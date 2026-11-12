package com.employee.management.department.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee.management.config.OpenApiConfig;
import com.employee.management.department.dto.CreateDepartmentRequest;
import com.employee.management.department.dto.DepartmentResponse;
import com.employee.management.department.dto.UpdateDepartmentRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Department Management APIs")
@RequestMapping("/api/departments")
public interface DepartmentApi {

    @Operation(
            summary = "Create a department",
            description = "Creates a new department using the provided name and description."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Department created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    ref = OpenApiConfig.BAD_REQUEST_RESPONSE
            ),
            @ApiResponse(
                    responseCode = "409",
                    ref = OpenApiConfig.CONFLICT_RESPONSE
            )
    })
    @PostMapping
    ResponseEntity<DepartmentResponse> createDepartment(
            @Valid @RequestBody CreateDepartmentRequest request);

    @Operation(
            summary = "List all departments",
            description = "Returns every department ordered as provided by the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Departments returned successfully"
    )
    @GetMapping
    ResponseEntity<List<DepartmentResponse>> getAllDepartments();

    @Operation(
            summary = "Get a department by id",
            description = "Returns a single department for the given identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Department returned successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    ref = OpenApiConfig.NOT_FOUND_RESPONSE
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable UUID id);

    @Operation(
            summary = "Update a department",
            description = "Updates the name and description of an existing department."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Department updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    ref = OpenApiConfig.BAD_REQUEST_RESPONSE
            ),
            @ApiResponse(
                    responseCode = "404",
                    ref = OpenApiConfig.NOT_FOUND_RESPONSE
            ),
            @ApiResponse(
                    responseCode = "409",
                    ref = OpenApiConfig.CONFLICT_RESPONSE
            )
    })
    @PutMapping("/{id}")
    ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDepartmentRequest request);

    @Operation(
            summary = "Delete a department",
            description = "Deletes the department for the given identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Department deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    ref = OpenApiConfig.NOT_FOUND_RESPONSE
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDepartment(@PathVariable UUID id);
}
