package com.jr.springboot.cruddemo.service;

import com.jr.springboot.cruddemo.dao.EmployeeRepository;
import com.jr.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null;

        if(result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            throw new RuntimeException("Not found the Employee with ID - " + theId);
        }
        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
