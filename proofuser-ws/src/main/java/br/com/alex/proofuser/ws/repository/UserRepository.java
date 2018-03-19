package br.com.alex.proofuser.ws.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.alex.proofuser.ws.view.User;

public interface UserRepository extends MongoRepository<User, String> {
		public User findByEmail(String email);

		public User findById(String id);
}
