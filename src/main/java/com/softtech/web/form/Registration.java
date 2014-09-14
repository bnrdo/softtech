package com.softtech.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.web.annotation.PasswordMatch;
import com.softtech.web.annotation.UserExist;
import com.softtech.web.validation.Step1Validation;
import com.softtech.web.validation.Step2Validation;
import com.softtech.web.validation.Step3Validation;

@PasswordMatch(message = "The password fields must match", groups = Step3Validation.class)
public class Registration {
	
	@NotEmpty(groups = Step1Validation.class, message = "Cannot be empty")
	private String title;
	
	@Pattern(regexp="^(?!Select Category).*$", groups = Step1Validation.class, message = "Please select a category")
	private String category;
	
	@Pattern(regexp="^(?!Select Technology).*$", groups = Step1Validation.class, message = "Please select a technology")
	private String technology;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	@UserExist(groups =  Step3Validation.class, message = "Username is already existing")
	private String username;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	private String password;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	private String retypePassword;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	private String firstname;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	private String lastname;
	
	private String middleInitial;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	private String contact;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	private String address;
	
	@NotEmpty(groups = Step3Validation.class, message = "Cannot be empty")
	private String email;
	
	@NotNull(groups = Step2Validation.class)
	private MultipartFile resume;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MultipartFile getResume() {
		return resume;
	}
	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}
}
