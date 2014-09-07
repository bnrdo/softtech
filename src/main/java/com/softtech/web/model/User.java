package com.softtech.web.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String userCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	
	@OneToMany(mappedBy="user")
	private Collection<Address> addresses;
	
	@OneToMany(mappedBy="user")
	private Collection<Contact> contacts;
	
	private String firstName;
	
	private String lastName;
	
	private String middleInitial;
	
	@OneToOne
	@JoinColumn(name="recruiter_id")
	private User recruiter;
	
	@ElementCollection
	@CollectionTable(name="PromoCodes", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="promoCode")
	private Collection<String> promoCodes;
	
	@ElementCollection
	@CollectionTable(name="Notes", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="note")
	private Collection<String> notes;
	
	@OneToMany(mappedBy="owner")
	private Collection<UserAccount> userAccounts;
	
	public User() { }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public Collection<Address> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}
	
	public Collection<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	public User getRecruiter() {
		return recruiter;
	}
	
	public void setRecruiter(User recruiter) {
		this.recruiter = recruiter;
	}
	
	public Collection<String> getPromoCodes() {
		return promoCodes;
	}
	
	public void setPromoCodes(Collection<String> promoCodes) {
		this.promoCodes = promoCodes;
	}
	
	public Collection<String> getNotes() {
		return notes;
	}
	
	public void setNotes(Collection<String> notes) {
		this.notes = notes;
	}
	
	public Collection<UserAccount> getUserAccounts() {
		return userAccounts;
	}
	
	public void setUserAccounts(Collection<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}
	public String getFullName(){
		String first = StringUtils.isNotBlank(firstName) ? firstName : " ";
		String last = StringUtils.isNotBlank(lastName) ? lastName : " ";
		return first + " " + last;
	}
	
}