package com.jr.demo.rest;

import com.jr.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student ID not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return the ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add another exception handler... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return the ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
}
