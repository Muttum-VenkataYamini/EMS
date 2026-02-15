package com.yamini.ems.employee.service;

import com.yamini.ems.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId , EmployeeDto employeeDto);

    String deleteEmployee(Long id);



}
