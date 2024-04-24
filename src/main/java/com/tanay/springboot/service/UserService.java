package com.tanay.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tanay.springboot.dto.UserRegistrationDTO;
import com.tanay.springboot.model.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDTO userRegistrationDTO);

}
