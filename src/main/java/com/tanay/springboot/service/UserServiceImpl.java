package com.tanay.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanay.springboot.dao.UserDAO;
import com.tanay.springboot.dto.UserRegistrationDTO;
import com.tanay.springboot.model.Role;
import com.tanay.springboot.model.User;

@Service
public class UserServiceImpl implements UserService {
	
private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	@Override
	public User save(UserRegistrationDTO userRegistrationDTO) {
		User user = new User(userRegistrationDTO.getFirstName(), 
				userRegistrationDTO.getLastName(), userRegistrationDTO.getEmail(),
				passwordEncoder.encode(userRegistrationDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
		return userDAO.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userDAO.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
