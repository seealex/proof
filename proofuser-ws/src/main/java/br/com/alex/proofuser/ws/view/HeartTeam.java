package br.com.alex.proofuser.ws.view;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

public class HeartTeam {
	
	@ApiModelProperty(hidden=true)
	@Id
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
