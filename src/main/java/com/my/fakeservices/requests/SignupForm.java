package com.my.fakeservices.requests;


import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
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
	
	@Transient
	private MultipartFile file;

}