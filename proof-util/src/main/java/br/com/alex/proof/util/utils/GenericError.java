package br.com.alex.proof.util.utils;

import java.util.List;

public class GenericError {

	private String code;

	private List<ErrorBean> errors;
	private String description;
	
	public List<ErrorBean> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorBean> errors) {
		this.errors = errors;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
