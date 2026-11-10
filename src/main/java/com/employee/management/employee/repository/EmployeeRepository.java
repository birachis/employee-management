package com.employee.management.employee.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.employee.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, UUID> {

            boolean existsByEmail(String email);

            List<Employee> findByDepartmentId(UUID departmentId);

}