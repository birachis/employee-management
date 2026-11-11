package com.employee.management.department.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.department.entity.Department;

public interface DepartmentRepository
        extends JpaRepository<Department, UUID> {

    public boolean existsByName(String name);

}