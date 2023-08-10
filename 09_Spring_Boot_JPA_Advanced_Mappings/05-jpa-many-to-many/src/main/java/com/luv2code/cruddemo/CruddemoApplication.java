package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO)	{
		return runner->	{
			deleteStudent(appDAO);
			// deleteCourse(appDAO);
			// addMoreCoursesForStudents(appDAO);
			// findStudentAndCourse(appDAO);
			// findCourseAndStudents(appDAO);
			// createCourseAndStudents(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
		// Create the instructor
		Instructor tempInstructor = new Instructor("Sika", "Tanjim", "sadtanjim@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/notfound404", "Love 2 Code :) !!!");

		// Associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Save the instructor
		// Note : This will also save the details object
		// Because of CascadeType.ALL
		System.out.println("Saving Instructor : " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done :)");

// 		Create the instructor
//		Instructor tempInstructor = new Instructor("Sika", "Tanjim", "sika@gmail.com");
//
//		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/notfound404", "Love 2 code !!!");
//
//		// Associate the objects
//		tempInstructor.setInstructorDetail(tempInstructorDetail);
//

	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding Instructor with ID : " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("TempInstructor : " + instructor);

		System.out.println("TempInstructor Details only : " + instructor.getInstructorDetail());
	}

	private void findInstructorDetailById(AppDAO appDAO) {
		int id = 4;
		System.out.println("Finding Instructor-Detail by id : " + id);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("InstructorDetail : " + instructorDetail);

		System.out.println("Associated Instructor : " + instructorDetail.getInstructor());

		System.out.println("Done");
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int id  = 10;
		System.out.println("Deleting Instructor-Detail by id : " + +id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// Create the instructor
		Instructor tempInstructor = new Instructor("Sika", "Tanjim", "sikaTanjim@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/NotFound404", "Love 2 Code !!!");
		// Associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse = new Course("Java -- The Ultimate Guide");
		Course tempCourse2 = new Course("Kotlin -- The Ultimate Guide");

		// add course to instructor
		tempInstructor.addCourse(tempCourse);
		tempInstructor.addCourse(tempCourse2);

		// Saving the instructor
		// *** Note : This will also save the courses
		// because of CascadeType.PERSIST

		System.out.println("Saving the instructor :)" + tempInstructor);
		System.out.println("The courses : " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done ;)");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		// This method only works if "fetch Type" is set to 'Eager' in Instructor Class
		int theId = 1;
		System.out.println("Finding the Instructor with id : " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor : " + instructor);
		System.out.println("The associated courses : " + instructor.getCourses());

		System.out.println("Done :)");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor : " + instructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);

		System.out.println("The associated courses : " + instructor.getCourses());

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id : " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor : " + instructor);
		System.out.println("Associated course with Instructor : " + instructor.getCourses());
	}

	private void updateCoursById(AppDAO appDAO) {
		int id = 10;

		System.out.println("Finding the Course with id : " + id);
		Course course = appDAO.findCourseById(id);

		System.out.println("Updating the Course");
		course.setTitle("Horizon -- Ultimate guide for peace");

		appDAO.update(course);

		System.out.println("Done :) Updated Course");
	}

	private void deleteInstructorById(AppDAO appDAO) {
		int id= 1;

		System.out.println("Deleting instructor by id : " + id);

		appDAO.deleteInstructorById(id);

		System.out.println("Done :(, Deleted Instructor");
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10 ;
		System.out.println("Deleting Course with Id : " + id);

		appDAO.deleteCourseById(id);

		System.out.println("Done, Deleted Course :(");
	}

	private void createReviewsForCourses(AppDAO appDAO) {
		int id = 13;
		Course tempCourse = appDAO.findCourseById(id);

		Review review1 = new Review("The Course was superb, concise tutorial, great support. And all the topic was supplied accordingly");
		Review review2 = new Review("The Course was superb, concise tutorial, great support. And all the topic was supplied accordingly");

		tempCourse.addReview(review1);
		tempCourse.addReview(review2);

		// Now updating our Course.
		appDAO.update(tempCourse);

		System.out.println("Done, Updated Courses with reviews");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("Python - Mastery in 60 days");

		course.addReview(new Review("Great Course, Loved it !"));
		course.addReview(new Review("Cool Course, well done"));
		course.addReview(new Review("What a dumb course, you are an idiot!"));

		System.out.println("Saving Course with reivews");

		appDAO.save(course);

		System.out.println("Course : " + course);
		System.out.println("Reviews : " + course.getReviews());
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 15;

		System.out.println("Finding Course & Review(s) with id : " + id);
		var course = appDAO.findCourseAndReviewsById(id);

		System.out.println("Course : " + course);
		System.out.println("Associated Reviews : " + course.getReviews());
		System.out.println("Done");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 15;
		System.out.println("Deleting Course by id : " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done :(");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course course = new Course("Master Python -- Ultimate Guide for Python :)");

		Student student1 = new Student("Sayad", "Tanjim", "sayad@gmail.com");
		Student student2 = new Student("John", "Wick", "johnwick@gmail.com");
		Student student3 = new Student("Sika", "T", "sika@gmail.com");

		course.addStudent(student1);
		course.addStudent(student2);
		course.addStudent(student3);

		System.out.println("Saving a course with three students :)");

		appDAO.save(course);

		System.out.println("Done");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int id = 10;

		System.out.println("Finding Course with Students by courseId : " + id);
		Course course = appDAO.findCourseAndStudentsByCourseId(id);

		System.out.println("Course : " + course);
		System.out.println("Enrolled Students in this course : " + course.getStudents());

		System.out.println("Done ;)");
	}

	private void findStudentAndCourse(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding Student with enrolled courses by student Id : " + id);

		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Student : " + student);
		System.out.println("Enrolled Courses : " + student.getCourses());
		System.out.println("There you have it ;)");
	}

	private void addMoreCoursesForStudents(AppDAO appDAO) {
		int id = 2;

		Student student = appDAO.findStudentAndCoursesByStudentId(2);

		student.addCourse(new Course("Ace your Shooting skills -- Learn to shoot better in just 3 days"));
		student.addCourse(new Course("Agility Mastery -- Increase your agility by more than 30-40%"));

		appDAO.update(student);

		System.out.println("Updated Student :)");
	}

	private void deleteStudent(AppDAO appDAO) {
		int id = 3;
		System.out.println("Deleting Student by Id : " + id);
		appDAO.deleteStudentById(id);
		System.out.println("Done, Deleted :(");
	}
}