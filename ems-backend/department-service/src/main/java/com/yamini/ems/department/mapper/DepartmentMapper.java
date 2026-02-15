package com.yamini.ems.department.mapper;

import com.yamini.ems.department.dto.DepartmentRequest;
import com.yamini.ems.department.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.name());
        department.setDescription(request.description());
        return department;
    }

    public void updateEntity(Department department, DepartmentRequest request) {
        department.setName(request.name());
        department.setDescription(request.description());
    }
}