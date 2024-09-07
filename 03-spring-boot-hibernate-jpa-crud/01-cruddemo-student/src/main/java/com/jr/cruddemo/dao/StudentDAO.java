package com.jr.cruddemo.dao;

import com.jr.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student theStudent);

    Student findById(Integer id);
}
