package com.yamini.ems.employee.service;

import com.yamini.ems.employee.Utils.EmployeeUtil;
import com.yamini.ems.employee.dto.EmployeeDto;
import com.yamini.ems.employee.entity.Employee;
import com.yamini.ems.employee.exception.ResourceNotFoundException;
import com.yamini.ems.employee.mapper.EmployeeMapper;
import com.yamini.ems.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee emp = EmployeeUtil.findEmployeeByIdOrThrow(employeeRepository, id);
        return EmployeeMapper.mapToEmployeeDto(emp);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> emp = employeeRepository.findAll();
        return emp.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList()); // e-> employeemapper.maptoemployeedto(e)
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee emp = EmployeeUtil.findEmployeeByIdOrThrow(employeeRepository, employeeId);
        emp.setFirstName(updatedEmployee.getFirstName());
        emp.setLastName(updatedEmployee.getLastName());
        emp.setEmail(updatedEmployee.getEmail());
        Employee UpdatedEmp = employeeRepository.save(emp);
        return EmployeeMapper.mapToEmployeeDto(UpdatedEmp);
    }

    @Override
    public String deleteEmployee(Long id) {
        Employee emp = EmployeeUtil.findEmployeeByIdOrThrow(employeeRepository, id);
        employeeRepository.delete(emp);
        return "Employee deleted successfully with id: " + id;
    }


}
