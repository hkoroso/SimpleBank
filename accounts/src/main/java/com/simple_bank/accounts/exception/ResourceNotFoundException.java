package com.simple_bank.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

        public ResourceNotFoundException(String resourceName, String fiedlName, String fieldValue) {
            super(String.format("%s not found with the given input%s : '%s'", resourceName, fiedlName, fieldValue));
        }

}
