package com.example.e_wallet.dto;

import com.example.e_wallet.model.Transaction;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

    private String sender;
    private String receiver;
    private Long amount;
    private String comment;

    public Transaction to() {
        return Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .comment(comment)
                .externalTransactionId(UUID.randomUUID().toString())
                .build();
    }
}
