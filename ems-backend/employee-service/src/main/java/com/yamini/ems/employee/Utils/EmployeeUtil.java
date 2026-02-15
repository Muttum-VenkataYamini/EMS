package com.yamini.ems.employee.Utils;

import com.yamini.ems.employee.entity.Employee;
import com.yamini.ems.employee.exception.ResourceNotFoundException;
import com.yamini.ems.employee.repository.EmployeeRepository;

public class EmployeeUtil {
    public static Employee findEmployeeByIdOrThrow(EmployeeRepository employeeRepository, Long id){
        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: " + id));
    }
}
