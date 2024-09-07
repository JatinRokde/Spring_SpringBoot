package com.jr.cruddemo;

import com.jr.cruddemo.dao.StudentDAO;
import com.jr.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	// CommandLineRunner is from the SpringBoot Framework
	// It is executed after the Spring Beans have been loaded
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating a new Student object... ");
		Student tempStudent = new Student("Kunal", "Singla", "kunal.singla@cuchd.in");

		// save the student object
		System.out.println("Saving the Student object... ");
		studentDAO.save(tempStudent);

		// display the id of saved student
		System.out.println("Saved Student Successfully. Generated ID: " + tempStudent.getId());
	}
}
