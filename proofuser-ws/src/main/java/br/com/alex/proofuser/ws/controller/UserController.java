package br.com.alex.proofuser.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alex.proof.util.utils.ErrorBean;
import br.com.alex.proof.util.utils.ErrorException;
import br.com.alex.proof.util.utils.GenericError;
import br.com.alex.proofuser.ws.service.UserService;
import br.com.alex.proofuser.ws.view.User;

@RestController
@RequestMapping("/users")
public class UserController extends ErrorException<User> {
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody User userView, BindingResult br, UriComponentsBuilder b){
		log.debug("read user controller");

		if(br.hasErrors()) {
			log.debug("bean validation error on save user");
			genericError = new GenericError();
			genericError.setCode(HttpStatus.BAD_REQUEST.toString());
            
            List<ErrorBean> errorsBean = new ArrayList<>();
            
            br.getFieldErrors().parallelStream().forEach(s -> {
                ErrorBean erro = new ErrorBean();
                erro.setCode(s.getCode());
                erro.setField(s.getField());
                erro.setMessage(s.getDefaultMessage());;
                errorsBean.add(erro);
            	});
            
            genericError.setErrors(errorsBean);
            
			return new ResponseEntity<GenericError>(genericError, HttpStatus.BAD_REQUEST);
		}
		
		return userService.create(userView, b);
		
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable("id") String id){
		log.debug("read use controller");
		User user = userService.read(id);
		if(user != null) {
			return ResponseEntity.ok(userService.read(id));
		}
		else {
			genericError = new GenericError();
			genericError.setCode(HttpStatus.BAD_REQUEST.toString());
            
            List<ErrorBean> errorsBean = new ArrayList<>();
            
            
            ErrorBean erro = new ErrorBean();
            erro.setCode(HttpStatus.NOT_FOUND.toString());
            erro.setMessage("Usuário não localizado.");
            errorsBean.add(erro);
            
            
            genericError.setErrors(errorsBean);
            
			return new ResponseEntity<GenericError>(genericError, HttpStatus.NOT_FOUND);
		}
	}
	
	

}
