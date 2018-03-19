package br.com.alex.proof.ws.view;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class Campaign {
	
	@Id
	@ApiModelProperty(position = 1, notes = "Id Campaign", dataType = "String", required = true)
	private String id;
	
	@NotBlank
	@NotEmpty
	private String name;
	//Nome Da Campanha;
	
	@NotNull
	private String idHeartTeam;
	//ID do Time do Coração;
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date validityDate;
	//Data de Vigência;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdHeartTeam() {
		return idHeartTeam;
	}
	public void setIdHeartTeam(String idHeartTeam) {
		this.idHeartTeam = idHeartTeam;
	}
	public Date getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	
	
}
