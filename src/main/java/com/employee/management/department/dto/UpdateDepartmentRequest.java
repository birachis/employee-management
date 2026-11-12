
package com.employee.management.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to update an existing department")
public record UpdateDepartmentRequest(

        @Schema(
                description = "Department name",
                example = "Information Technology",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Department name is required")
        @Size(max = 100, message = "Department name cannot exceed 100 characters")
        String name,

        @Schema(
                description = "Department description",
                example = "Manages software, hardware and IT infrastructure."
        )
        @Size(max = 255, message = "Description cannot exceed 255 characters")
        String description

) {
}
