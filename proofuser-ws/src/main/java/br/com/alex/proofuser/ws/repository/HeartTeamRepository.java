package br.com.alex.proofuser.ws.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.alex.proofuser.ws.view.HeartTeam;

public interface HeartTeamRepository extends MongoRepository<HeartTeam, String> {
		
	 	public HeartTeam findByName(String name);
}
