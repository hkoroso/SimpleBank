package com.simple_bank.loans.service;

import com.simple_bank.loans.dto.LoansDto;

public interface LoansService {
    void createLoan(String mobileNumber);
    LoansDto fetchLoan(String mobileNumber);


    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String mobileNumber);
}
