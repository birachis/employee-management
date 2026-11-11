package com.employee.management.department.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.employee.management.common.exception.ConflictException;
import com.employee.management.common.exception.ResourceNotFoundException;
import com.employee.management.department.dto.CreateDepartmentRequest;
import com.employee.management.department.dto.DepartmentResponse;
import com.employee.management.department.entity.Department;
import com.employee.management.department.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {

        if (departmentRepository.existsByName(request.name())) {
            throw new ConflictException("Department name already exists");
        }

        Department department = new Department(
            request.name(),
            request.description()
        );

        Department savedDepartment = departmentRepository.save(department);

        return new DepartmentResponse(
                savedDepartment.getId(),
                savedDepartment.getName(),
                savedDepartment.getDescription(),
                savedDepartment.getCreatedAt()
        );
    }

    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(department -> new DepartmentResponse(
                        department.getId(),
                        department.getName(),
                        department.getDescription(),
                        department.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public DepartmentResponse getDepartmentById(UUID id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getCreatedAt()
        );
    }
}