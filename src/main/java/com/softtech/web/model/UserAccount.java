package com.softtech.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.softtech.web.util.PasswordUtil;

@Entity
@Table(name="useraccount")
public class UserAccount implements Serializable {
	
	private static final long serialVersionUID = -146003835169014512L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="owner_id")
	private User owner;
	
	private String username;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinTable(name="UserAccountsAndRoles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private List<Role> roles;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	private String email;
	
	private String passwordResetToken;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date passwordResetSentDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	public UserAccount() { }

	public UserAccount(int id, String username, String password, List<Role> roles,
			UserStatus status, Date creationDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.status = status;
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPasswordForReset() throws Exception {
		return PasswordUtil.generateDefaultPassword() + password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "UserAccount [owner=" + owner + ", username=" + username
				+ ", roles=" + roles + ", status=" + status + ", email="
				+ email + "]";
	}
	
	public String getRoleList(){
		return roles==null ? null : StringUtils.join(roles, ", ");
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public Date getPasswordResetSentDate() {
		return passwordResetSentDate;
	}

	public void setPasswordResetSentDate(Date passwordResetSentDate) {
		this.passwordResetSentDate = passwordResetSentDate;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}