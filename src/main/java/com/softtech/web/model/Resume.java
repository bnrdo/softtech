package com.softtech.web.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="resume")
public class Resume {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String fileName;
	
	@Lob
	private byte[] content;
	
	@OneToOne(mappedBy="resume")
	private JobSeekerProfile jobSeekerProfile;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	public Resume(){}

	public Resume(int id, String fileName, byte[] content,
			JobSeekerProfile jobSeekerProfile) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.content = content;
		this.jobSeekerProfile = jobSeekerProfile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public JobSeekerProfile getJobSeekerProfile() {
		return jobSeekerProfile;
	}

	public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		this.jobSeekerProfile = jobSeekerProfile;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
