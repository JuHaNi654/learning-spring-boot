package fi.resthubservice.rest.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommunityHubRepository extends CrudRepository<CommunityHub, Long> {
	@Query(value = "SELECT user.username FROM ((community_hub"
			+ " inner join member on community_hub.ch_id = member.communityhub_id)"
			+ " inner join user on member.user_id = user.id)"
			+ " where community_hub.ch_id = ? and user.username = ?;", nativeQuery = true)
	String findByCommunityMember(Long id, String username);
}
