package com.example.e_wallet.repositories;

import com.example.e_wallet.models.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByUserId(String userId);

    @Modifying
    @Transactional
    @Query("update Wallet w set w.balance = w.balance + :amount where w.id = :id")
    void updateWallet(Long id, Long amount);
}
