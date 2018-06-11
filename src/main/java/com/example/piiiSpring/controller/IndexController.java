package com.example.piiiSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import com.example.piiiSpring.model.*;

@Controller
public class IndexController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/result")
    public String result(@RequestParam(value = "empOneFirstName", defaultValue = "John") String empOneFirstName,
            @RequestParam(value = "empOneLastName", defaultValue = "Doe") String empOneLastName,
            @RequestParam(value = "empOneSalary", defaultValue = "18000.00") float empOneSalary,
            @RequestParam(value = "empTwoFirstName", defaultValue = "Ben") String empTwoFirstName,
            @RequestParam(value = "empTwoLastName", defaultValue = "Smith") String empTwoLastName,
            @RequestParam(value = "empTwoSalary", defaultValue = "21000.00") float empTwoSalary,
            Model model) {

        Employee empOne = new Employee(empOneFirstName, empOneLastName, empOneSalary);
        Employee empTwo = new Employee(empTwoFirstName, empTwoLastName, empTwoSalary);
        Employees em = new Employees();

        try {
            employeeRepository.save(empOne);
            employeeRepository.save(empTwo);
        } catch (Exception ex) {
            System.out.println("Error creating the user: " + ex.toString());
        }

        float avg = em.getAverageSalary(empOne, empTwo);
        float salarySum = em.getSumOfSalaries(empOne, empTwo);
        Employee richer = em.getEmployeeWithHigherSalary(empOne, empTwo);

        model.addAttribute("empOne", empOne);
        model.addAttribute("empTwo", empTwo);
        model.addAttribute("richer", richer);
        model.addAttribute("average", avg);
        model.addAttribute("sum", salarySum);
        return "result";
    }
    
    @RequestMapping("/employees")
    public String getAllEmployees(
            @RequestParam(value = "editedEmployee", required = false, defaultValue = "false") boolean editedEmployee,
            @RequestParam(value = "deletedEmployee", required = false, defaultValue = "false") boolean deletedEmployee,
            Model model) {

        Employees em = new Employees();
        Iterable<Employee> employeeList = employeeRepository.findAll();
        float avgSalary = em.getAverageSalary(employeeList);
        Employee richest = employeeRepository.findFirstByOrderBySalaryDesc();

        model.addAttribute("editedEmployee", editedEmployee);
        model.addAttribute("deletedEmployee", deletedEmployee);
        model.addAttribute("richest", richest);
        model.addAttribute("averageSalary", avgSalary);
        model.addAttribute("employees", employeeList);
        return "employees";
    }

    @GetMapping("/employee/{id}")
    public String getEmployee(@PathVariable("id") int id, Model model) {
        Employee employee = new Employee();
        try {
            employee = employeeRepository.findById(id);
        } catch (Exception ex) {
            System.out.println("Error getting the user: " + ex.toString());
        }
        model.addAttribute("employee", employee);
        return "employee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@RequestParam(value = "id") int empID,
            @RequestParam(value = "firstname") String empFirstname,
            @RequestParam(value = "lastname") String empLastname,
            @RequestParam(value = "salary") float empSalary) {
        Employee employee = new Employee(empID, empFirstname, empLastname, empSalary);
        try {
            employeeRepository.save(employee);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "redirect:/employees?editedEmployee=true";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam(value = "id") int id) {
        try {
            Employee employee = new Employee(id);
            employeeRepository.delete(employee);
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "redirect:/employees?deletedEmployee=true";
    }
}
