package br.com.alex.proof.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.validation.ObjectError;

import br.com.alex.proof.util.utils.ErrorBean;
import br.com.alex.proof.util.utils.ErrorException;
import br.com.alex.proof.util.utils.GenericError;
import br.com.alex.proof.ws.service.CampaignService;
import br.com.alex.proof.ws.view.Campaign;

@RestController
@RequestMapping("/campaigns")
public class CampaignController extends ErrorException<Campaign>{
	private Logger log = LoggerFactory.getLogger(CampaignController.class);
	
	@Autowired
	private CampaignService campaignService;
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Campaign campaignView, BindingResult br, UriComponentsBuilder b){
		log.debug("read campaign controller");

		if(br.hasErrors()) {
			log.debug("bean validation error on save campaign");
			genericError = new GenericError();
			genericError.setCode(HttpStatus.BAD_REQUEST.toString());
            
            List<ErrorBean> errorsBean = new ArrayList<>();
            
            br.getAllErrors().parallelStream().forEach(s -> {
                ErrorBean erro = new ErrorBean();
                erro.setCode(s.getCode());
                erro.setMessage(s.getDefaultMessage());;
                errorsBean.add(erro);
            	});
            
            genericError.setErrors(errorsBean);
            
			return new ResponseEntity<GenericError>(genericError, HttpStatus.BAD_REQUEST);
		}
		
		campaignService.create(campaignView);
	
	    
	    UriComponents uriComponents = 
	            b.path("/campaigns/{id}").buildAndExpand(campaignView.getId());

	    return ResponseEntity.created(uriComponents.toUri()).build();
	    
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable("id") String id){
		log.debug("read campaign controller");
		
		return ResponseEntity.ok(campaignService.read(id));
		
	}
	
	@GetMapping()
	public ResponseEntity<?> readAll(){
		log.debug("read campaign controller");
		
		return ResponseEntity.ok(campaignService.readAll());
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody Campaign campaignView, BindingResult br, UriComponentsBuilder b){
		log.debug("read campaign controller");
		
		campaignService.update(id, campaignView);
		
		return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
	}
	
/*	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePartial(@PathVariable("id") Long id, @Valid @RequestBody Campaign campaignView, BindingResult br){
		log.debug("read campaign controller");
		
		return ResponseEntity.ok(campaignService.updatePartial(id, campaignView));
		
	}*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id){
		log.debug("read campaign controller");
		
		return ResponseEntity.ok(campaignService.delete(id));
		
	}
	
}
