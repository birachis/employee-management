package com.employee.management.department.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.department.api.DepartmentApi;
import com.employee.management.department.dto.CreateDepartmentRequest;
import com.employee.management.department.dto.DepartmentResponse;
import com.employee.management.department.dto.UpdateDepartmentRequest;
import com.employee.management.department.service.DepartmentService;

@RestController
public class DepartmentController implements DepartmentApi {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public ResponseEntity<DepartmentResponse> createDepartment(
            CreateDepartmentRequest request) {

        DepartmentResponse response =
                departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {

        List<DepartmentResponse> departments =
                departmentService.getAllDepartments();

        return ResponseEntity.ok(departments);
    }

    @Override
    public ResponseEntity<DepartmentResponse> getDepartmentById(UUID id) {

        DepartmentResponse department =
                departmentService.getDepartmentById(id);

        return ResponseEntity.ok(department);
    }

    @Override
    public ResponseEntity<DepartmentResponse> updateDepartment(
            UUID id,
            UpdateDepartmentRequest request) {

        DepartmentResponse response =
                departmentService.updateDepartment(id, request);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteDepartment(UUID id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}
