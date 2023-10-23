package com.simple_bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(name = "Response", description = "Schema to hold Response details")
@Data @AllArgsConstructor
public class ResponseDto {

    //http status
    @Schema(description = "HTTP Status code", example = "400")
    private String statusCode;
    @Schema(description = "Error message", example = "Mobile number should be valid")
    private String statusMsg;

}
