package com.rishbootdev.springbootTesting.repository;

import com.rishbootdev.springbootTesting.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> getByEmail(String email);


}
