package com.jr.cruddemo;

import com.jr.cruddemo.dao.AppDAO;
import com.jr.cruddemo.entity.Course;
import com.jr.cruddemo.entity.Instructor;
import com.jr.cruddemo.entity.InstructorDetail;
import com.jr.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting the Course with ID: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("DONE!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println("Course: " + tempCourse);

		// print the associated reviews
		System.out.println("Associated Reviews: " + tempCourse.getReviews());
		System.out.println("DONE!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Mastering Data Structures and Algorithms");

		// add some reviews
		tempCourse.addReview(new Review("One of the best course for DSA!"));
		tempCourse.addReview(new Review("Great Course.. worth watching!"));
		tempCourse.addReview(new Review("For sure best for placements and skill-ups!"));

		// save the course.. and leverage the cascade ALL
		System.out.println("Saving the Course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("DONE!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting the Course with ID: " + theId);
		appDAO.deleteCourseById(theId);

		System.out.println("DELETED!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		// find the course
		System.out.println("Finding Course ID: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating Course with ID: " + theId);
		tempCourse.setTitle("Machine Learning BootCamp");

		appDAO.update(tempCourse);

		System.out.println("DONE!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding Instructor with ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		// update the instructor
		System.out.println("Updating the Instructor with ID: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("DONE");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding Instructor with ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("The associated Courses: " + tempInstructor.getCourses());

		System.out.println("DONE!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor ID: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding Courses for Instructor ID: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("The associated Courses: " + tempInstructor.getCourses());
		System.out.println("DONE!");
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
