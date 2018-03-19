package br.com.alex.proof.util.utils;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import br.com.alex.proof.util.utils.GenericError;

public abstract class ErrorException<T> {
	protected List<GenericError> errors;
	protected GenericError genericError;
	protected long viewsWithError;
	
	protected void httpClientError (HttpClientErrorException e) throws JSONException {
		genericError = new GenericError();
		genericError.setCode(e.getStatusCode().toString());
		genericError.setDescription(e.getResponseBodyAsString());
		errors.add(genericError);		
	}
	
	protected void httpServerError (HttpServerErrorException e) throws JSONException {
		genericError = new GenericError();
		
		JSONObject json = new JSONObject(e.getResponseBodyAsString());
        genericError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        genericError.setDescription(json.getString("message"));
        errors.add(genericError);
        
	}
	
}
