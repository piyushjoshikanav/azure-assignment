package com.nagarro.training.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	Employee findByEmail(String email);
}
