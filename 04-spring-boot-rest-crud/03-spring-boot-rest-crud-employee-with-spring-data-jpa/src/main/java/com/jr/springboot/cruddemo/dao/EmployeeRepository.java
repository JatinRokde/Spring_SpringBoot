package com.jr.springboot.cruddemo.dao;

import com.jr.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it.. no code required
}
