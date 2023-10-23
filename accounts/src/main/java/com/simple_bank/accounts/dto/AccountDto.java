package com.simple_bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Account", description = "Schema to hold Account details")
public class AccountDto {
    @Schema(description = "Account number", example = "1234567890")
   @NotEmpty(message = "Account number cannot be empty")
   @Pattern(regexp = "(^$|\\d{10})", message = "Account number should be valid")
    private Long accountNumber;
    @Schema(description = "Account type", example = "Savings")
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;
    @Schema(description = "Branch address", example = "1000N 4th Street, Fairfield, IA 52557")
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;

}
