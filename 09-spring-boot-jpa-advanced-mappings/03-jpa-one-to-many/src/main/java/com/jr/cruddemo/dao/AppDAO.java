package com.jr.cruddemo.dao;

import com.jr.cruddemo.entity.Course;
import com.jr.cruddemo.entity.Instructor;
import com.jr.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save (Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);
}
