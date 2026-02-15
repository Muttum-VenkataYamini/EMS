package com.mvyamini.ems_backend.Utils;

import com.mvyamini.ems_backend.entity.Employee;
import com.mvyamini.ems_backend.exception.ResourceNotFoundException;
import com.mvyamini.ems_backend.repository.EmployeeRepository;

public class EmployeeUtil {
    public static Employee findEmployeeByIdOrThrow(EmployeeRepository employeeRepository, Long id){
        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: " + id));
    }
}
