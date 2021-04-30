package com.my.fakeservices.requests;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignupForm {

    @NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String password;
		
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String mobile;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}