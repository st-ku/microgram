package com.stku.microgram.service;

import com.stku.microgram.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
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
            Object response = rabbitTemplate.convertSendAndReceive(queueName , userDTO);
            if (response == null) {
                return false;
            }
            return (Boolean) response;
        } catch (AmqpException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

