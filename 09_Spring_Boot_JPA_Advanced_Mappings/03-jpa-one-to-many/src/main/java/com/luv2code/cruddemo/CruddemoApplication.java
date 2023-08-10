package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.criteria.CriteriaBuilder;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO)	{
		return runner->	{
			deleteCourse(appDAO);

			// deleteInstructorById(appDAO);
			// updateCoursById(appDAO);
			// updateInstructorById(appDAO);
			// findInstructorWithCoursesJoinFetch(appDAO);
			// findCoursesForInstructor(appDAO);
			// findInstructorWithCourses(appDAO);
			//createInstructorWithCourses(appDAO);
			// deleteInstructorDetailById(appDAO);
			// findInstructorDetailById(appDAO);
			// findInstructor(appDAO);
			// createInstructor(appDAO);
			// deleteInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
		// Create the instructor
		Instructor tempInstructor = new Instructor("John", "Wick", "jwick@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/notfound404", "Love 2 shoot !!!");

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

//	private void deleteInstructor(AppDAO appDAO) {
//		int id = 3;
//
//		System.out.println("Deleting the instructor by Id : " + id);
//
//		appDAO.deleteInstructorById(id);
//
//		System.out.println("Done :(");
//	}

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
		Instructor tempInstructor = new Instructor("Micheal", "Jack", "jackmike@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/mikeJack", "Singer (Pop King)");
		// Associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse = new Course("Air Guiter -- The Ultimate Guide");
		Course tempCourse2 = new Course("English Fluency -- Get IELTS 7+ Band");

		// add course to instructor
		tempInstructor.add(tempCourse);
		tempInstructor.add(tempCourse2);

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
}