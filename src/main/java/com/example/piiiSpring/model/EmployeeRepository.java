package com.example.piiiSpring.model;

import org.springframework.data.repository.CrudRepository;
import com.example.piiiSpring.model.Employee;
import java.util.ArrayList;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
    public Employee findById(int id);
    
    public Employee findFirstByOrderBySalaryDesc();
    
}
