package com.yamini.ems.department.service;

import com.yamini.ems.department.dto.DepartmentRequest;
import com.yamini.ems.department.dto.DepartmentResponse;
import com.yamini.ems.department.entity.Department;
import com.yamini.ems.department.exception.DepartmentNotFoundException;
import com.yamini.ems.department.mapper.DepartmentMapper;
import com.yamini.ems.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Transactional
    public DepartmentResponse create(DepartmentRequest request) {
        if (repository.existsByName(request.name())) {
            throw new IllegalArgumentException("Department name already exists");
        }

        Department department = mapper.toEntity(request);
        department = repository.save(department);
        return toResponse(department);
    }

    public DepartmentResponse getById(Long id) {
        Department dept = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        return toResponse(dept);
    }

    public List<DepartmentResponse> getAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public DepartmentResponse update(Long id, DepartmentRequest request) {
        Department dept = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        if (!dept.getName().equals(request.name()) && repository.existsByName(request.name())) {
            throw new IllegalArgumentException("Department name already exists");
        }

        mapper.updateEntity(dept, request);
        dept = repository.save(dept);
        return toResponse(dept);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private DepartmentResponse toResponse(Department dept) {
        return new DepartmentResponse(dept.getId(), dept.getName(), dept.getDescription());
    }
}