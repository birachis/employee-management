package com.employee.management.department.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Department information returned by the API")
public record DepartmentResponse(

        @Schema(
                description = "Unique department identifier",
                example = "328950d1-0648-4f63-9f6c-c7def0d0c3cc"
        )
        UUID id,

        @Schema(
                description = "Department name",
                example = "Information Technology"
        )
        String name,

        @Schema(
                description = "Department description",
                example = "Manages software, hardware and IT infrastructure."
        )
        String description,

        @Schema(
                description = "Date and time the department was created",
                example = "2026-11-11T10:14:47.854878"
        )
        LocalDateTime createdAt

) {
}