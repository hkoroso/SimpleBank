package com.simple_bank.accounts.service.impl;

import com.simple_bank.accounts.constants.AccountsConstants;
import com.simple_bank.accounts.dto.AccountDto;
import com.simple_bank.accounts.dto.CustomerDto;
import com.simple_bank.accounts.entity.Accounts;
import com.simple_bank.accounts.entity.Customer;
import com.simple_bank.accounts.exception.CustomerAlreadyExistsException;
import com.simple_bank.accounts.exception.ResourceNotFoundException;
import com.simple_bank.accounts.mapper.AccountsMapper;
import com.simple_bank.accounts.mapper.CustomerMapper;
import com.simple_bank.accounts.repository.AccountsRepository;
import com.simple_bank.accounts.repository.CustomerRepository;
import com.simple_bank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists"
            + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createAccount(savedCustomer));

    }


    private Accounts createAccount(Customer customer) {

        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber",  mobileNumber)
                );

          Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                 () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
            );

          CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
          customerDto.setAccountDto(AccountsMapper.mapAccountsDto(account,new AccountDto()));

    return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber",  mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}
