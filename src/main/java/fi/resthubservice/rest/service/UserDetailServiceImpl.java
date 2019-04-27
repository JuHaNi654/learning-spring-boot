package fi.resthubservice.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.resthubservice.rest.domain.User;
import fi.resthubservice.rest.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPassword(),
				true, true, true, true,
				AuthorityUtils.createAuthorityList(currUser.getRole()));
		return user;
	}
}
