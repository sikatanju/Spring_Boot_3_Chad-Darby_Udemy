package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.criteria.CriteriaBuilder;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO)	{
		return runner->	{
			deleteInstructor(appDAO);
			// createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
//		// Create the instructor
//		Instructor tempInstructor = new Instructor("Sika", "Tanjim", "sika@gmail.com");
//
//		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/notfound404", "Love 2 code !!!");
//
//		// Associate the objects
//		tempInstructor.setInstructorDetail(tempInstructorDetail);
//

		// Create the instructor
		Instructor tempInstructor = new Instructor("TT", "Tanjim", "tttanjim@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/notfound404", "Love 2 game !!!");

		// Associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Save the instructor
		// Note : This will also save the details object
		// Because of CascadeType.ALL
		System.out.println("Saving Instructor : " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done :)");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding Instructor with ID : " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("TempInstructor : " + instructor);

		System.out.println("TempInstructor Details only : " + instructor.getInstructorDetail());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 5;

		System.out.println("Deleting the instructor by Id : " + id);

		appDAO.deleteInstructorById(id);

		System.out.println("Done :(");
	}
}
