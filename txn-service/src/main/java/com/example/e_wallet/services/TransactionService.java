package com.example.e_wallet.services;

import com.example.e_wallet.dto.TransactionDTO;
import com.example.e_wallet.model.Transaction;
import com.example.e_wallet.model.TransactionStatus;
import com.example.e_wallet.repositories.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private JSONParser jsonParser = new JSONParser();

    private static final String TRANSACTION_CREATED_TOPIC = "transaction-created";
    private static final String WALLET_UPDATED_TOPIC = "wallet-updated";

    public Transaction send(TransactionDTO transactionDTO) {
        Transaction transaction = transactionDTO.to();
        transaction.setStatus(TransactionStatus.PENDING);
        transaction = this.transactionRepository.save(transaction);

        //Publish kafka Event
        try {
            this.kafkaTemplate.send(TRANSACTION_CREATED_TOPIC, objectMapper.writeValueAsString(transaction));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return transaction;
    }

    @KafkaListener(topics = WALLET_UPDATED_TOPIC, groupId = "transaction-group")
    public void updateTransaction(String msg) throws ParseException {
        JSONObject eventData = (JSONObject) jsonParser.parse(msg);

        String walletUpdatedStatus = eventData.get("status").toString();
        String externalTxnId = eventData.get("externalTransactionId").toString();

        TransactionStatus transactionStatus = walletUpdatedStatus.equals("SUCCESS") ? TransactionStatus.SUCCESS : TransactionStatus.FAILED;
        this.transactionRepository.updateTransaction(externalTxnId, transactionStatus);

        // TODO: Publish transaction completed event (which will be listened by a notification service to send out relevant communication)
    }
}
