package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    // Define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager theEntityManager)   {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

//    @Override
//    @Transactional
//    public void deleteInstructorById(int theId) {
//        // Retreiving the instructor for deleting by their id.
//        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
//
//        // Deleting the instructor.
//        entityManager.remove(tempInstructor);
//    }

    @Override
    @Transactional
    public void saveInstructorDetail(InstructorDetail instructorDetail) {
        entityManager.persist(instructorDetail);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id)   {
        TypedQuery query = entityManager.createQuery("select i from Instructor i "
                                                        + "Join Fetch i.courses "
                                                        + "Join Fetch i.instructorDetail "
                                                        + "where i.id = :data", Instructor.class);
        query.setParameter("data", id);

        // Instructor instructor = (Instructor) query.getSingleResult();

        return (Instructor) query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve instructorDetail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // Create Query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        // Execute query
//        List<Course> courses = query.getResultList();
//        return courses;
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int id)    {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void update(Course course)   {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id)    {
        // Retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, id);

        List<Course> courses = instructor.getCourses();

        // Break association of all courses for instructor
        for (var temp : courses)    {
            temp.setInstructor(null);
        }

        // Lastly remove/delete the instructor by calling below method
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id)    {
        Course course = entityManager.find(Course.class, id);

        entityManager.remove(course);
    }
}
