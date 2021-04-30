package com.my.fakeservices.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.fakeservices.model.User;
import com.my.fakeservices.repository.UserRepository;
import com.my.fakeservices.requests.LoginForm;
import com.my.fakeservices.requests.SignupForm;
import com.my.fakeservices.services.UserService;
import com.my.fakeservices.utility.JWTUtility;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/auth")
public class ServicesController {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/")
	public String home() {
		return "Welcome";
	}

	@GetMapping("/applications")
	public String getApplications() {
		return "These are the applications";
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registration(@Valid @RequestBody SignupForm registrationForm) {

		System.out.println(registrationForm);

		if (userRepository.existsByEmail(registrationForm.getEmail())) {
			return new ResponseEntity("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		User user = new User(registrationForm.getFirstName(), registrationForm.getLastName(),
				encoder.encode(registrationForm.getPassword()), registrationForm.getEmail(),
				registrationForm.getMobile(), true);

		userRepository.save(user);

		return new ResponseEntity("User " + user.getFirstName() + " is registered successfully!", HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody LoginForm loginForm) throws Exception {
		System.out.println(loginForm);

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = userService.loadUserByUsername(loginForm.getEmail());

		final String token = jwtUtility.generateToken(userDetails);

		return new ResponseEntity(
				"Login Successful - please use the token " + token + "   " + userDetails.getUsername(), HttpStatus.OK);
	}

}
