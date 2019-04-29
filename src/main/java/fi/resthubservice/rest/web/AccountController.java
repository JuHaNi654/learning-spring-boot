package fi.resthubservice.rest.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.resthubservice.rest.domain.SignUpForm;
import fi.resthubservice.rest.domain.User;
import fi.resthubservice.rest.domain.UserRepository;

@RestController
public class AccountController {
	private UserRepository repository;
	
	//Creates new user from given data
	@PostMapping(value="/newuser", consumes = {
			MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	}, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity saveUser(@RequestBody SignUpForm signUpForm) {
		if (signUpForm.getPassword().equals(signUpForm.getPasswordCheck())) {
			String password = signUpForm.getPassword();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hashPassword = bc.encode(password);
			
			User newUser = new User();
			newUser.setUsername(signUpForm.getUsername());
			newUser.setPassword(hashPassword);
			newUser.setEmail(signUpForm.getEmail());
			newUser.setRole("USER");
			
			if (repository.findByUsername(signUpForm.getUsername()) == null) {
				repository.save(newUser);
				return new ResponseEntity<>("Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Username is already in use", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>("Given passwords doesent match", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
