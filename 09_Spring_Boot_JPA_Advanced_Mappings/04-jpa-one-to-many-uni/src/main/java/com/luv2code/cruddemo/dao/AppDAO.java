package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    // void deleteInstructorById(int theId);

    void saveInstructorDetail(InstructorDetail theInstructorDetail);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    Course findCourseById(int id);

    void update(Course course);

    void deleteInstructorById(int theId);

    void deleteCourseById(int id);

    void save(Course course);

    Course findCourseAndReviewsById(int id);
}
