/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.piiiSpring.controller;

//import org.springframework.stereotype.Controller;
import com.example.piiiSpring.model.Employee;
import com.example.piiiSpring.model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Kashif
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeAPI {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(path = "/all")
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @RequestMapping(path = "/employee/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        Employee employee = employeeRepository.findById(id);
        employee = employee == null ? new Employee() : employee;
        return employee;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(employee);
        } catch (Exception ex) {
            System.out.println("Error inserting the user:" + ex.toString());
        }
        return employee;
    }

    @RequestMapping(path = "/employee", method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(employee);
        } catch (Exception ex) {
            System.out.println("Error updating the user:" + ex.toString());
        }
        return employee;
    }

    @RequestMapping(path = "/employee/{id}", method = RequestMethod.DELETE)
    public Employee deleteEmployee(@PathVariable("id") int id) {
        Employee employee = new Employee(id);
        try {
            employeeRepository.delete(employee);
        } catch (Exception ex) {
            System.out.println("Error deleting the user:" + ex.toString());
            //return "Error deleting employee";
        }
        return employee;
    }

}
