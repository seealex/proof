package br.com.alex.proofuser.ws.view;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class User {
	
	@ApiModelProperty(hidden=true)
	@Id
	private String id;
	
	@ApiModelProperty(position=0)
	private String fullName;
	//Nome Completo;
	
	@ApiModelProperty(position=1)
	private String email;
	//E-mail;
	
	@ApiModelProperty(position=2)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date birthdate;
	//Data de Nascimento;
	
	@ApiModelProperty(position=3)
	@DBRef
	private HeartTeam heartTeam;
	//Meu Time do Coração;
	
	@ApiModelProperty(hidden=true)
	@DBRef
	private List<Campaign> campaignList;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public HeartTeam getHeartTeam() {
		return heartTeam;
	}

	public void setHeartTeam(HeartTeam heartTeam) {
		this.heartTeam = heartTeam;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Campaign> getCampaignList() {
		return campaignList;
	}

	public void setCampaignList(List<Campaign> campaignList) {
		this.campaignList = campaignList;
	}
	
}
