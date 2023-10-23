package com.simple_bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account details")
public class CustomerDto {

    @Schema(description = "Customer name", example = "Jojo")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 30, message = "Name should have atleast 2 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Customer email")
    private String email;

    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number should be valid")
    @Schema(description = "Customer mobile number")

    private String mobileNumber;
    @Schema(description = "Customer Account details")
    private AccountDto accountDto;


}
