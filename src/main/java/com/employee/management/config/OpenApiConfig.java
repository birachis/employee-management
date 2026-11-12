package com.employee.management.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.employee.management.common.exception.ErrorResponse;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;

@Configuration
public class OpenApiConfig {

    /**
     * Reusable error response components. Controllers reference these with
     * {@code @ApiResponse(ref = ...)} so the error contract lives in one place.
     */
    public static final String BAD_REQUEST_RESPONSE = "#/components/responses/BadRequest";
    public static final String NOT_FOUND_RESPONSE = "#/components/responses/ResourceNotFound";
    public static final String CONFLICT_RESPONSE = "#/components/responses/Conflict";

    @Bean
    public OpenAPI employeeManagementOpenAPI() {

        Components components = new Components()
                .addResponses("BadRequest", errorResponse(
                        "The request contains invalid input",
                        400,
                        "Bad Request",
                        "name: Department name is required"))
                .addResponses("ResourceNotFound", errorResponse(
                        "The requested resource was not found",
                        404,
                        "Not Found",
                        "Department not found"))
                .addResponses("Conflict", errorResponse(
                        "The request conflicts with existing data",
                        409,
                        "Conflict",
                        "Department name already exists"));

        // The error responses reference ErrorResponse by name, so make sure the
        // schema is present in components even though no endpoint declares it directly.
        ModelConverters.getInstance()
                .readAll(ErrorResponse.class)
                .forEach(components::addSchemas);

        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")
                        .description("REST API for managing employees and departments.")
                        .version("1.0.0"))
                .components(components);
    }

    private static ApiResponse errorResponse(
            String description,
            int status,
            String error,
            String message) {

        Map<String, Object> example = new LinkedHashMap<>();
        example.put("status", status);
        example.put("error", error);
        example.put("message", message);
        example.put("timestamp", "2026-11-12T15:30:00");

        return new ApiResponse()
                .description(description)
                .content(new Content().addMediaType(
                        org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                        new MediaType()
                                .schema(new Schema<>().$ref("#/components/schemas/ErrorResponse"))
                                .example(example)));
    }
}
