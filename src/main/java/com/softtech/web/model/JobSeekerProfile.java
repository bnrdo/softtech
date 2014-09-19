package com.softtech.web.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="jobseekerprofile")
public class JobSeekerProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="techjob_id")
	private TechJob techJobApplied;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="owner_id")
	private User owner;
	
	@OneToOne
	@JoinColumn(name="resume_id")
	private Resume resume;
	
	private String tags;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	private boolean active;
	
	public JobSeekerProfile(){}
	
	public JobSeekerProfile(int id, TechJob techJobApplied, User owner,
			Resume resume, String tags) {
		super();
		this.id = id;
		this.techJobApplied = techJobApplied;
		this.owner = owner;
		this.resume = resume;
		this.tags = tags;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TechJob getTechJobApplied() {
		return techJobApplied;
	}

	public void setTechJobApplied(TechJob techJobApplied) {
		this.techJobApplied = techJobApplied;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
