package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController   (EmployeeService theEmployeeService)    {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{studentId}")
    public Employee findById(@PathVariable int studentId)  {
        Employee theEmployee = employeeService.findById(studentId);

        if (theEmployee == null)
            throw new RuntimeException("Employee id not found - " + studentId);

        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)  {
        // Also just in cas they pass an id in JSON, set id to 0
        // This is to force a save of new item, instead of Update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee)   {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)    {
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null)
            throw new RuntimeException("No employee found by id : " + employeeId);

        employeeService.deleteById(employeeId);
        return "Deleted Employee id : " + employeeId;
    }
}