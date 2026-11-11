package com.employee.management.department.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DepartmentResponse(
        UUID id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}