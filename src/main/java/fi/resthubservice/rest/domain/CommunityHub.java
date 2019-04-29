package fi.resthubservice.rest.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CommunityHub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chId;
	
	@Column(nullable = false)
	private String hubName;
	
	@Column(nullable = false)
	private String isPrivate;
	
	@Column(nullable = false)
	private Date createdDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "communityhub")
	@JsonIgnore
	public List<Member> member;

	
	public CommunityHub() {
		super();
	}


	public CommunityHub(String hubName, Date createdDate, String isPrivate) {
		super();
		this.hubName = hubName;
		this.createdDate = createdDate;
		this.isPrivate = isPrivate;
	}


	public Long getChId() {
		return chId;
	}


	public void setChId(Long chId) {
		this.chId = chId;
	}


	public String getHubName() {
		return hubName;
	}


	public void setHubName(String hubName) {
		this.hubName = hubName;
	}
	
	public String isPrivate() {
		return isPrivate;
	}


	public void setPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public List<Member> getMember() {
		return member;
	}


	public void setMember(List<Member> member) {
		this.member = member;
	}


	
	
	
	
}
