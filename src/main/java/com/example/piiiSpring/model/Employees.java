package com.example.piiiSpring.model;

//import com.example.piiiSpring.model.Employee;
/**
 *
 * @author k1552723
 */
public class Employees {

    public float getAverageSalary(Employee empOne, Employee empTwo) {
        float avgSalary = (empOne.getSalary() + empTwo.getSalary()) / 2;
        return avgSalary;
    }

    public float getSumOfSalaries(Employee empOne, Employee empTwo) {
        return empOne.getSalary() + empTwo.getSalary();
    }

    public Employee getEmployeeWithHigherSalary(Employee empOne, Employee empTwo) {
        Employee richer = null;
        if (empOne.getSalary() > empTwo.getSalary()) {
            richer = empOne;
        } else if (empOne.getSalary() < empTwo.getSalary()) {
            richer = empTwo;
        }
        return richer;
    }

    public float getAverageSalary(Iterable<Employee> employeeList) {
        float totalSalaries = 0;
        int count = 0;
        for (Employee e : employeeList) {
            totalSalaries = totalSalaries + e.getSalary();
            count++;
        }
        return totalSalaries / count;
    }

}
