package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO  {
    // Define field for EntityManager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager)    {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Create a query.
        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // Creating a query to retrieve an Employee by their ID...
        Employee theEmployee = entityManager.find(Employee.class, theId);

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);

        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);
        // remove employee
        entityManager.remove(theEmployee);
    }
}
