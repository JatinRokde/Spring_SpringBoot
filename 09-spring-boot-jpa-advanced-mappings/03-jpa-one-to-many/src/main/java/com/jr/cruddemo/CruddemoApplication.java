package com.jr.cruddemo;

import com.jr.cruddemo.dao.AppDAO;
import com.jr.cruddemo.entity.Course;
import com.jr.cruddemo.entity.Instructor;
import com.jr.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			
			findInstructorWithCourses(appDAO);
		};
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor ID: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("The associated Courses: " + tempInstructor.getCourses());

		System.out.println("DONE!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Mohit", "Singh", "mohit.singh@cuchd.in");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.mohitsingh.com/youtube", "Video Games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Java - 17 - The Ultimate Guide");
		Course tempCourse2 = new Course("Python - A to Z Bootcamp");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		// NOTE: This will also save courses because of CascadeType.PERSIST
		System.out.println("Saving Instructor: " + tempInstructor);
		System.out.println("The Courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("DONE!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting Instructor Detail ID: " + theId);

		appDAO.deleteInstructorDetailById(theId);
		System.out.println("DELETED!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("The Instructor: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("The Associated Instructor: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting the Instructor with ID: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("DELETED!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding the Instructor with ID: " +theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("Associated Instructor Details: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Kunal", "Singla", "kunal.singla@cuchd.in");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.kunlasingla.com/youtube", "ML models");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		// this will also save the details object
		System.out.println("Saving Instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("SAVED!");
	}
}