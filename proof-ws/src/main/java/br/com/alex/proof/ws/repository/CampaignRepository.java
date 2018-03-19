package br.com.alex.proof.ws.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.alex.proof.ws.view.Campaign;

public interface CampaignRepository extends MongoRepository<Campaign, String> {
		@Query(value = "{ 'id': ?0, 'validityDate' : {$gte : ?1 }}")
	 	public Campaign findByIdAndDate(String id, LocalDateTime localDateTime);
	 	
	 	@Query(value = "{ 'validityDate' : {$gte : ?0 }}")
	    public List<Campaign> findAll(LocalDateTime localDateTime);
	 	
	 	@Query(value = "{ 'validityDate' : {$gte : ?0, $lte : ?1 }}")
	    public List<Campaign> findAllBetween(LocalDateTime localDateTime, Date validityDate);
}
