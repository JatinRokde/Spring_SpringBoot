package com.jr.cruddemo.dao;

import com.jr.cruddemo.entity.Instructor;

public interface AppDAO {
    void save (Instructor theInstructor);

    Instructor findInstructorById(int theId);
}
