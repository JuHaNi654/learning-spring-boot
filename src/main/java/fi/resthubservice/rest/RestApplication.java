package fi.resthubservice.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.resthubservice.rest.domain.CommunityHub;
import fi.resthubservice.rest.domain.CommunityHubRepository;
import fi.resthubservice.rest.domain.JoinedMemberKey;
import fi.resthubservice.rest.domain.Member;
import fi.resthubservice.rest.domain.MemberRepository;
import fi.resthubservice.rest.domain.User;
import fi.resthubservice.rest.domain.UserRepository;

@SpringBootApplication
public class RestApplication {

	@Autowired
	private UserRepository repository;
	@Autowired
	private CommunityHubRepository chrepository;
	@Autowired
	private MemberRepository mrepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);

	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			User user = new User("admin", "$2a$04$IEbRJGn1avY3xT7mCB2Qkusm2CN6RnPWGX6lXqD923MlTRR3Vuq2u", "ADMIN", "testi@testi.fi");
			repository.save(user);
			CommunityHub hub = new CommunityHub("TestingHub", new Date());
			chrepository.save(hub);
			
			Member member = new Member(new JoinedMemberKey(user.getId(), hub.getChId()), user, hub, "admin");
			mrepository.save(member);
		};
	}

}
