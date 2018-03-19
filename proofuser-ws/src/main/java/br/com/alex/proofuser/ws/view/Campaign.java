package br.com.alex.proofuser.ws.view;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Campaign {

	@Id
	private String id;

	private String name;
	// Nome Da Campanha;

	private String idHeartTeam;
	// ID do Time do Coração;

	private Date validityDate;
	// Data de Vigência;

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

	@Override
	public boolean equals(Object anObject) {
		if (!(anObject instanceof Campaign)) {
			return false;
		}
		Campaign otherMember = (Campaign) anObject;
		return otherMember.getId().equals(getId());
	}

}
