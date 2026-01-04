package com.example.e_wallet.services;

import com.example.e_wallet.configs.KafkaConfig;
import com.example.e_wallet.dto.CreateUserDTO;
import com.example.e_wallet.models.User;
import com.example.e_wallet.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String USER_CREATED_TOPIC = "user-created";

    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public User create(CreateUserDTO createUserDTO) {
        User user = this.userRepository.save(createUserDTO.toUser());

        //Publish Kafka event on user creation
        try {
           String data = objectMapper.writeValueAsString(user); // Convert java object to a String
            this.kafkaTemplate.send(USER_CREATED_TOPIC, data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return user;
    }
}
