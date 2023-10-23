package com.simple_bank.accounts.service;

import com.simple_bank.accounts.dto.CustomerDto;

public interface IAccountsService {
/**
*param customerDto-CustomerDto-object
 **/
 void createAccount(CustomerDto customerDto);


CustomerDto fetchAccount(String mobileNumber);
boolean updateAccount(CustomerDto customerDto);

boolean deleteAccount(String mobileNumber);
}
