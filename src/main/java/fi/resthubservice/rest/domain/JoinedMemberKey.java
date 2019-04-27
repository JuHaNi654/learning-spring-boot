package fi.resthubservice.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class JoinedMemberKey implements Serializable {
	
	@Column(name="user_Id")
	private Long userId;
	
	@Column(name="communityhub_id")
	private Long chId;

	
	public JoinedMemberKey() {
		super();
	}


	public JoinedMemberKey(Long userId, Long chId) {
		super();
		this.userId = userId;
		this.chId = chId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getChId() {
		return chId;
	}


	public void setChId(Long chId) {
		this.chId = chId;
	}
	
	
	
}
