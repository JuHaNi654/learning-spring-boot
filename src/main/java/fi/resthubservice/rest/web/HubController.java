package fi.resthubservice.rest.web;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.resthubservice.rest.domain.CommunityHub;
import fi.resthubservice.rest.domain.CommunityHubRepository;
import fi.resthubservice.rest.domain.MemberRepository;
import fi.resthubservice.rest.domain.User;
import fi.resthubservice.rest.domain.UserRepository;
import fi.resthubservice.rest.service.AuthenticationService;

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
	public @ResponseBody Optional<CommunityHub> oneHub(@PathVariable Long id, HttpServletRequest req, HttpServletResponse res) {
		Authentication auth = AuthenticationService.getAuthentication((HttpServletRequest)req);
		String authenticatedUser = (String) auth.getPrincipal();
		if (chrepository.findByCommunityMember(id, authenticatedUser) != null) {
			return chrepository.findById(id);
		} else {
			return chrepository.findById(id);
		}
	}
	
	
}
