package com.jr.cruddemo;

import com.jr.cruddemo.dao.StudentDAO;
import com.jr.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
			// createStudent(studentDAO);
			createMultipleStudent(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all Students... ");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted Row count = " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting the Student with ID: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting the Student with ID: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "Scooby"
		System.out.println("Updating Student... ");
		myStudent.setFirstName("Scooby");

		// update the Student
		studentDAO.update(myStudent);

		// display the updated Student
		System.out.println("Updated Student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of Students
		List<Student> theStudents = studentDAO.findByLastName("Bhasin");

		// display the list of Students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display the list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating a new Student... ");
		Student tempStudent = new Student("Maulik", "Bhatnagar", "maulik.bhatnagar@cuchd.in");

		// save the student
		System.out.println("Saving the Student... ");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved Student. Generated ID: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving Student with the ID: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the Student: " + myStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// create multiple student objects
		System.out.println("Creating 3 new Student object... ");
		Student tempStudent1 = new Student("Madhur", "Tyagi", "madhur.tyagi@cuchd.in");
		Student tempStudent2 = new Student("Vishal", "Singh", "vishal.singh@cuchd.in");
		Student tempStudent3 = new Student("Kush", "Bhasin", "kush.bhasin@cuchd.in");

		// saving the students
		System.out.println("Saving the Students... ");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
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
