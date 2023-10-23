package com.simple_bank.accounts.controller;


import com.simple_bank.accounts.constants.AccountsConstants;
import com.simple_bank.accounts.dto.CustomerDto;
import com.simple_bank.accounts.dto.ErrorResponseDto;
import com.simple_bank.accounts.dto.ResponseDto;
import com.simple_bank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD APIs for Accounts in EazyBank", description = "The Accounts API")
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
    private IAccountsService accountsService;
@Operation(summary = "Create a new account REST API",
           description = "This API creates a new account in the EazyBank system",
           tags = {"CRUD APIs for Accounts in EazyBank"})
@ApiResponse(responseCode = "201", description = "Account created successfully")
@PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
accountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));

    }
@Operation(summary = "Fetch account details REST API",
           description = "This API fetches the account details for the given mobile number",
           tags = {"CRUD APIs for Accounts in EazyBank"})
@ApiResponse(responseCode = "200", description = "Account details fetched successfully")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|\\d{10})",
            message = "Mobile number should be valid") String mobileNumber
                                                           ){
        CustomerDto customerDto=accountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);

}
@Operation(summary = "Update account details REST API",
           description = "This API updates the account details for the given mobile number",
           tags = {"CRUD APIs for Accounts in EazyBank"})
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account details updated successfully"),
        @ApiResponse(responseCode = "417", description = " Exception Failed"),
        @ApiResponse(responseCode = "500",
                description = "Account details not modified",
               content = @Content(
                       schema = @Schema(implementation = ErrorResponseDto.class)))

})
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated=accountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));

    }
@Operation(summary = "Delete account REST API",
           description = "This API deletes the account for the given mobile number",
           tags = {"CRUD APIs for Accounts in EazyBank"})
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
        @ApiResponse(responseCode = "417", description = " Exception Failed"),
        @ApiResponse(responseCode = "500",
                description = "Account not deleted",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)))
})
@DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number should be valid") String mobileNumber){
        boolean isDeleted=accountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));

    }

}
