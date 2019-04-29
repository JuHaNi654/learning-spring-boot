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
	//Saving example data
	@Bean
	CommandLineRunner runner() {
		return args -> {
			//Saving users to the database
			User user = new User("rolli", "$2a$04$IEbRJGn1avY3xT7mCB2Qkusm2CN6RnPWGX6lXqD923MlTRR3Vuq2u", "OWNER", "testi@testi.fi");
			repository.save(user);
			User user2 = new User("user", "$2a$06$7U.M8ba1Hpg/41OiapCTquxJv5A7vsxSUhSRy33wlgqGOjoHJE8ca", "USER", "user@testi.fi");
			repository.save(user2);
			
			// Saving hubs to the database
			CommunityHub hub = new CommunityHub("TestingHub",  new Date(), "no");
			chrepository.save(hub);
			CommunityHub hub2 = new CommunityHub("UserHub", new Date(), "no");
			chrepository.save(hub2);
			
			// Saving hub members to the database
			Member member = new Member(new JoinedMemberKey(user.getId(), hub.getChId()), user, hub, "admin");
			mrepository.save(member);
			Member member2 = new Member(new JoinedMemberKey(user2.getId(), hub2.getChId()), user2, hub2, "admin");
			mrepository.save(member2);
		};
	}

}
