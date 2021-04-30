package com.my.fakeservices.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.fakeservices.model.User;
import com.my.fakeservices.repository.UserRepository;
import com.my.fakeservices.requests.SignupForm;

@RestController
@RequestMapping("/api")
public class ServicesController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/home")
	public String home() {
		System.out.println("welcome");
		return "Welcome";
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registration(@Valid @RequestBody SignupForm registrationForm) {

		System.out.println(registrationForm);

		if (userRepository.existsByEmail(registrationForm.getEmail())) {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>("Username is already taken!", HttpStatus.BAD_REQUEST);
			return responseEntity;
		}

		User user = new User(registrationForm.getFirstName(), registrationForm.getLastName(),
				registrationForm.getPassword(), registrationForm.getEmail(), registrationForm.getMobile(), true);

		userRepository.save(user);

		return new ResponseEntity<String>("User " + user.getFirstName() + " is registered successfully!", HttpStatus.OK);
	}

}
