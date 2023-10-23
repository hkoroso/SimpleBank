package com.simple_bank.accounts.repository;

import com.simple_bank.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long customerId);
@Transactional// This annotation is required for deleteByCustomerId to work for consistency of data
@Modifying
    void deleteByCustomerId(Long customerId);
}
