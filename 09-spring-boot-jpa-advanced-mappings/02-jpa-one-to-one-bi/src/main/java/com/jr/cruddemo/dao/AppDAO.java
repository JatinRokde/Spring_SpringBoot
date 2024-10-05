package com.jr.cruddemo.dao;

import com.jr.cruddemo.entity.Instructor;
import com.jr.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save (Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
