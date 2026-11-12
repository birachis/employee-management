package com.employee.management.common.exception;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard API error response")
public record ErrorResponse(
    @Schema(description = "HTTP status code")
    int status,

    @Schema(description = "HTTP status description")
    String error,

    @Schema(description = "Details about the error")
    String message,

    @Schema(description = "Date and time when the error occurred")
    LocalDateTime timestamp
) {}