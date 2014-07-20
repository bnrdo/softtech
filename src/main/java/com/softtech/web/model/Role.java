package com.softtech.web.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name="role")
public class Role {
	
	public static final Role USER = new Role("ROLE_USER");
	
	public static final Role ADMIN = new Role("ROLE_ADMIN");
	
	public static final Role SUPERVISOR = new Role("ROLE_SUPERVISOR");
	
	public static final Role JOB_SEEKER = new Role("ROLE_JOB_SEEKER");
	
	public static final Role RECRUITER_INTERNAL = new Role("ROLE_RECRUITER_INTERNAL");
	
	public static final Role RECRUITER = new Role("ROLE_RECRUITER");
	
	public static final String ROLE_PREFIX = "ROLE_";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String roleName;
		
	@ManyToMany(mappedBy = "roles")
	private List<UserAccount> users;
	
	private String description;
	
	public Role() { }
	
	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(int id, String roleName, List<UserAccount> users) {
		this.id = id;
		this.roleName = roleName;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserAccount> getUsers() {
		return users;
	}

	public void setUsers(List<UserAccount> users) {
		this.users = users;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Role other = (Role) obj;
		
		if (roleName == null) {
			if (other.roleName != null) return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		
		return true;
		
	}

	@Override
	public String toString() {
		return roleName==null ? null : StringUtils.removeStartIgnoreCase(roleName, ROLE_PREFIX);		
	}
	
}