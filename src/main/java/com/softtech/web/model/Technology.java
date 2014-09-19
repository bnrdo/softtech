package com.softtech.web.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="technology")
public class Technology {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String code;
	private String name;
	private String description;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="category_id")
	private Category category;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	public Technology(){}
	
	public Technology(int id, String code, String name, String description) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
	
}
