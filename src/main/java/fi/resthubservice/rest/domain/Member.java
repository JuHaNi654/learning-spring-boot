package fi.resthubservice.rest.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Member {
	
	@EmbeddedId
	JoinedMemberKey mId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("user_Id")
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("communityhub_id")
	@JoinColumn(name = "communityhub_id", nullable = false, insertable = false, updatable = false)
	private CommunityHub communityhub;
	
	private String hubRole;


	public Member() {
		super();
	}


	public Member(JoinedMemberKey mId, User user, CommunityHub communityhub, String hubRole) {
		super();
		this.mId = mId;
		this.user = user;
		this.communityhub = communityhub;
		this.hubRole = hubRole;
	}


	public JoinedMemberKey getmId() {
		return mId;
	}


	public void setmId(JoinedMemberKey mId) {
		this.mId = mId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public CommunityHub getCommunityhub() {
		return communityhub;
	}


	public void setCommunityhub(CommunityHub communityhub) {
		this.communityhub = communityhub;
	}


	public String getHubRole() {
		return hubRole;
	}


	public void setHubRole(String hubRole) {
		this.hubRole = hubRole;
	}

	
	
	

}
