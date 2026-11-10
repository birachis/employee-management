package com.employee.management.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateDepartmentRequest(

        @NotBlank(message = "Department name is required")
        @Size(max = 100, message = "Department name cannot exceed 100 characters")
        String name,

        @Size(max = 255, message = "Description cannot exceed 255 characters")
        String description

) {
}