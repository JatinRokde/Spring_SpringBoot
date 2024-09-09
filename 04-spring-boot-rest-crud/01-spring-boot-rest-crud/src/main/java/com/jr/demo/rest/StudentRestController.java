package com.jr.demo.rest;

import com.jr.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;
        // define @PostConstruct to load the student data.. only once!
        @PostConstruct
        public void loadData() {
            theStudents = new ArrayList<>();
            theStudents.add(new Student("Vishal", "Singh"));
            theStudents.add(new Student("Rahul", "Gujar"));
            theStudents.add(new Student("Maulik", "Bhatnagar"));
        }

    // define endpoint for "/students" - return the list of all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return the student at index
    @GetMapping("/students/{studentId}")
    // return the student at that index
    public Student getStudent(@PathVariable int studentId) {
        return theStudents.get(studentId);
    }
}
