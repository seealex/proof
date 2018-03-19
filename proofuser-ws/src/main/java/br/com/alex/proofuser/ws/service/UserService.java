package br.com.alex.proofuser.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import br.com.alex.proof.util.utils.ErrorBean;
import br.com.alex.proof.util.utils.ErrorException;
import br.com.alex.proof.util.utils.GenericError;
import br.com.alex.proofuser.ws.repository.HeartTeamRepository;
import br.com.alex.proofuser.ws.repository.UserRepository;
import br.com.alex.proofuser.ws.view.Campaign;
import br.com.alex.proofuser.ws.view.HeartTeam;
import br.com.alex.proofuser.ws.view.User;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.reflect.TypeToken;

@Service
public class UserService {

	@Autowired
	HeartTeamRepository heartTeamRepository;
	
	@Autowired
	UserRepository userRepository;
	
	RestTemplate restTemplate = new RestTemplate();
	
	public ResponseEntity<?> create(User userView, UriComponentsBuilder b) {
		User user = userRepository.findByEmail(userView.getEmail());
		
		//user exists
		if(user != null){
			if(user.getCampaignList().size() == 0) {				
				ResponseEntity<List<Campaign>> res = restTemplate.exchange("http://localhost:8080/campaigns", HttpMethod.GET, null, new ParameterizedTypeReference<List<Campaign>>() {});
				
				List<Campaign> newList = res.getBody().parallelStream().filter(
						s -> s.getIdHeartTeam().equals(user.getHeartTeam().getId())
						).collect(Collectors.toList());
				user.setCampaignList(newList);
				
				userRepository.save(user);
				
				res.getBody().clear();
				res.getBody().addAll(newList);
				return res;

			}else {
				
				ResponseEntity<List<Campaign>> res = restTemplate.exchange("http://localhost:8080/campaigns", HttpMethod.GET, null, new ParameterizedTypeReference<List<Campaign>>() {});

				List<Campaign> campNew = res.getBody().parallelStream().filter(s -> s.getIdHeartTeam().equals(user.getHeartTeam().getId())).collect(Collectors.toList());

				campNew.removeAll(user.getCampaignList());

				user.getCampaignList().addAll(campNew);
				userRepository.save(user);
				
				GenericError genericError = new GenericError();
				genericError.setCode(HttpStatus.BAD_REQUEST.toString());
				genericError.setDescription("Cadastro já foi efetuado");
	            genericError.setErrors(null);
				return new ResponseEntity<GenericError>(genericError, HttpStatus.BAD_REQUEST); 
			}
		}
		else {
			String a = restTemplate.getForObject("http://localhost:8080/campaigns", String.class);
			Type listType = new TypeToken<ArrayList<Campaign>>(){}.getType();
			List<Campaign> campList = new Gson().fromJson(a, listType);
			
			HeartTeam heartTeam = heartTeamRepository.findByName(userView.getHeartTeam().getName());
			
			if(heartTeam == null) {
				heartTeamRepository.save(userView.getHeartTeam());
			}else {
				userView.setHeartTeam(heartTeam);
			}
			
			//its new
			userView.setCampaignList(campList.parallelStream().filter(s -> s.getIdHeartTeam().equals(heartTeam.getId())).collect(Collectors.toList()));
			userRepository.save(userView);
			
			UriComponents uriComponents = 
		            b.path("/users/{id}").buildAndExpand(userView.getId());
			HttpHeaders headers = new HttpHeaders();
			  headers.setLocation(uriComponents.toUri());
			    
			 return new ResponseEntity<Object>(null, headers, HttpStatus.CREATED);
		}
	}

	public User read(String id){
		User user = userRepository.findById(id);
		return user;
	}

}
