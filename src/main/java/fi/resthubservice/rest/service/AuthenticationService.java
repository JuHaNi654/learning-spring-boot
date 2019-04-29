package fi.resthubservice.rest.service;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {
	static final long EXPIRATIONTIME = 864_000_000; // Time in ms
	static final String SIGNINGKEY = "SecretKeyForTheDoomRolliTheBaiterMaisterFuckerTheBestTheRandomTheGodGamerTheHackerMan987456321TheHolostTheManly";
	static final String PREFIX = "Bearer";
	
	// After Successful login software gives user token to identify
	static public void addToken(HttpServletResponse res, String username) {
		String token = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
				.compact();
		res.addHeader("Authorization", PREFIX + " " + token);
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
	}
	
	// Checks authentication token
	static public Authentication getAuthentication(HttpServletRequest req) {
		String token = req.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey(SIGNINGKEY)
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
			}
		}
		return null;
	}
	
}
