package com.jr.demo.rest;

import com.jr.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    // define endpoint for "/students" - return the list of all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> theStudents = new ArrayList<>();
        theStudents.add(new Student("Vishal", "Singh"));
        theStudents.add(new Student("Rahul", "Gujar"));
        theStudents.add(new Student("Maulik", "Bhatnagar"));

        return theStudents;
    }
}
