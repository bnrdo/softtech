package com.softtech.web.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="techjob")
public class TechJob {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="tech_id")
	private Technology technology;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="job_id")
	private Job job;
	
	private String code;
	private String jobDescription;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePosted;
	
	@Enumerated(EnumType.STRING)
	private TechJobStatus status;
	
	public TechJob(){}

	public TechJob(int id, Technology technology, Job job, String code,
			String jobDescription, Date datePosted, TechJobStatus status) {
		super();
		this.id = id;
		this.technology = technology;
		this.job = job;
		this.code = code;
		this.jobDescription = jobDescription;
		this.datePosted = datePosted;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public TechJobStatus getStatus() {
		return status;
	}

	public void setStatus(TechJobStatus status) {
		this.status = status;
	}
}
