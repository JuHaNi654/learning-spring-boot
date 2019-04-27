package fi.resthubservice.rest.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.resthubservice.rest.domain.CommunityHub;
import fi.resthubservice.rest.domain.CommunityHubRepository;
import fi.resthubservice.rest.domain.MemberRepository;
import fi.resthubservice.rest.domain.UserRepository;

@RestController
public class HubController {
	
	@Autowired
	private CommunityHubRepository chrepository;
	@Autowired
	private UserRepository urepository;
	@Autowired
	private MemberRepository mrepository;
	
	@GetMapping(value ="/hubs")
	public Iterable<CommunityHub> getHubs() {
		return chrepository.findAll();
	}

	@GetMapping(value = "/hubs/{id}")
	public Object oneHub(@PathVariable Long id) {
		return chrepository.findById(id);
	}
	
	
}
