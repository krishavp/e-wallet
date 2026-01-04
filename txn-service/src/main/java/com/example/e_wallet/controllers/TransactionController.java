package com.example.e_wallet.controllers;

import com.example.e_wallet.dto.TransactionDTO;
import com.example.e_wallet.model.Transaction;
import com.example.e_wallet.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("")
    public Transaction send(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.send(transactionDTO);
    }
}
