package com.simple_bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "Schema to hold Error Response details")
@Data @AllArgsConstructor
@Setter
@Getter

public class ErrorResponseDto {
    @Schema(description = "API Path invoked by client", example = "/api/create")
    private String apiPath;
    @Schema(description = "HTTP Status code", example = "400")
    private HttpStatus errorCode;
    @Schema(description = "Error message", example = "Mobile number should be valid")
    private String errorMessage;
    @Schema(description = "Error time", example = "2021-07-04T12:12:12.123")
    private LocalDateTime errorTime;


}
