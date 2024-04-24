package com.tanay.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanay.springboot.model.User;

@Repository
public interface UserDAO extends JpaRepository<User , Long> {
	User findByEmail(String email);

}
