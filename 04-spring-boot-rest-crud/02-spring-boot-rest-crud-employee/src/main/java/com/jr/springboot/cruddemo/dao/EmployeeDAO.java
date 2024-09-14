package com.jr.springboot.cruddemo.dao;

import com.jr.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
