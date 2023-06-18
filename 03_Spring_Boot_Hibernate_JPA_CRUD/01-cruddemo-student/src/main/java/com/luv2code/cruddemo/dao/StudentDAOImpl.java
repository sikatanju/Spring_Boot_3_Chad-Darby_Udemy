package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll()  {
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student order by lastName", Student.class);
        /*   Here look at this, order by lastName, now lastName is the JPA entity meaning this is the field from Student class, not the column name from the database.
        This also goes for Student, this is JPA Entity, not table name
        */
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName)   {
        // Creating Query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student where lastName= :theData", Student.class);
        // Set query Parameters
        theQuery.setParameter("theData", theLastName);
        // Return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional // Since, we're updating, we need to add @Transactional. For read only there were no need to add this annotation.
    public void update (Student theStudent)  {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete (int id) {
        Student myStudent = entityManager.find(Student.class, id);
        entityManager.remove(myStudent);
    }

    @Override
    @Transactional
    public int deleteAll()  {
        int numRowsDeleted = entityManager.createQuery("Delete from Student").executeUpdate();
        return numRowsDeleted;
    }
}
