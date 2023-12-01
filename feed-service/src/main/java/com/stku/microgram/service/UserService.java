package com.stku.microgram.service;

import com.stku.microgram.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final String queueName = "users.auth";

    public Boolean isAuth(String username, String password) {
        UserDTO userDTO = new UserDTO(username, password);

        try {
            // Sending message and waiting for response
            Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive(queueName , userDTO);

            // Check if the response is truthy (adjust as needed)
            return response;
        } catch (AmqpException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

