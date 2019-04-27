package fi.resthubservice.rest.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	Optional<User> findById(Long id);
}
