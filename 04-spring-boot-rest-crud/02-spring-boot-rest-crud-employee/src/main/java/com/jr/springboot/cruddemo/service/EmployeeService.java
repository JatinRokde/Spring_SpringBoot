package com.jr.springboot.cruddemo.service;

import com.jr.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
}
