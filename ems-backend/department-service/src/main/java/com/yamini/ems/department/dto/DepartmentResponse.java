// dto/DepartmentResponse.java
package com.yamini.ems.department.dto;

public record DepartmentResponse(
        Long id,
        String name,
        String description
) {}