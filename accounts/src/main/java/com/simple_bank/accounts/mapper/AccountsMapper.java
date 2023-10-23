package com.simple_bank.accounts.mapper;

import com.simple_bank.accounts.dto.AccountDto;
import com.simple_bank.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountDto mapAccountsDto(Accounts accounts, AccountDto accountDto ){
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static Accounts mapToAccounts(AccountDto accountDto, Accounts accounts){

        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}
