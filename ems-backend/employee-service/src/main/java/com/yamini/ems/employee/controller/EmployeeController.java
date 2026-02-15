package com.yamini.ems.employee.controller;

import com.yamini.ems.employee.dto.EmployeeDto;
import com.yamini.ems.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @PostMapping("/addemployee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto empDto = employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>(empDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        EmployeeDto emp = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(emp);
    }

    @GetMapping("/getallemployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> emp = employeeService.getAllEmployees();
        return ResponseEntity.ok(emp);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        EmployeeDto emp = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(emp);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        String msg = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(msg);
    }


}
