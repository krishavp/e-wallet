package com.example.e_wallet.repositories;

import com.example.e_wallet.model.Transaction;
import com.example.e_wallet.model.TransactionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Modifying
    @Transactional
    @Query("update Transaction t set t.status = ?2 where t.externalTransactionId = ?1")
    void updateTransaction(String externalTransactionId, TransactionStatus transactionStatus);
}
