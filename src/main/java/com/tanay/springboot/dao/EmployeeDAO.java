package com.tanay.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanay.springboot.model.Employee;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {
	

}
