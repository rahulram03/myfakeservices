package com.my.fakeservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.fakeservices.repository.UserRepository;

@Service
public class UserService{

	@Autowired
	private UserRepository userRepository;
}
