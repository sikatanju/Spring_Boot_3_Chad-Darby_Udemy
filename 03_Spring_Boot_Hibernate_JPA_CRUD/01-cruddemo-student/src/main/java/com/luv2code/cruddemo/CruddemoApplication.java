package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
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
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO)	{
		return runner ->	{
			 createMultipleStudents(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		System.out.println("Deleting all the students :(");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Number of rows deleted : " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 7;
		System.out.println("Deleting student with id : " + studentId);
		studentDAO.delete(7);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;

		System.out.println("Updating the student with id : " + studentId);
		Student myStudent = studentDAO.findById(studentId);
		System.out.println("Updating the student :)");

		// Change the first name to Scooby
		myStudent.setFirstName("Sika");
		studentDAO.update(myStudent);

		// Display updated student
		System.out.println("Updated Student" + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findByLastName("T");
		// Printing the list
		for (Student temp : list)
			System.out.println(temp);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findAll();

		for (Student temp : list)
			System.out.println(temp);
	}

	private void readStudent(StudentDAO studentDAO) {
		Student temp = new Student("Paul", "M", "paulm@gmail.com");

		studentDAO.save(temp);

		System.out.println("Saved student, generated ID : " + temp.getId());

		Student myStudent = studentDAO.findById(temp.getId());
		System.out.println("Found the student : " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO)	{
		System.out.println("Creating 3 student objects...");

		Student student1 = new Student("John", "Wick", "johnwick@gmail.com");
		Student student2 = new Student("Chris", "Paul", "chrispaul@gmail.com");
		Student student3 = new Student("Abul", "Ahmed", "abulahmed@gmail.com");

		// Saving three students....
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

		// Display id of the saved student
		System.out.print("Saved Student, generated Id : " + student1.getId() + ", " + student2.getId() + ", " + student3.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Dao", "pauldao@gmail.com");

		// save the student object
		System.out.println("Saving the Student");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved Student, Generated Id : " + tempStudent.getId());
	}
}
