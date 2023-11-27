package com.simple_bank.loans.service.impl;

import com.simple_bank.loans.constants.LoansConstants;
import com.simple_bank.loans.dto.LoansDto;
import com.simple_bank.loans.entity.Loans;
import com.simple_bank.loans.exception.LoanAlreadyExistsException;
import com.simple_bank.loans.exception.ResourceNotFoundException;
import com.simple_bank.loans.mapper.LoansMapper;
import com.simple_bank.loans.repository.LoansRepository;
import com.simple_bank.loans.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements LoansService {
    @Autowired
    private LoansRepository loansRepository;


    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loansOptional = loansRepository.findByMobileNumber(mobileNumber);
        if (loansOptional.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists");
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }


        @Override
        public LoansDto fetchLoan(String mobileNumber) {
            Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
            );
            return LoansMapper.mapToLoansDto(loans, new LoansDto());

        }


    private Loans createNewLoan(String mobileNumber) {
        Loans loans = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(randomLoanNumber));
        loans.setMobileNumber(mobileNumber);
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loans;
    }


    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return  true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

}
