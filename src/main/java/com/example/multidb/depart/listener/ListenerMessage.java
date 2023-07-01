package com.example.multidb.depart.listener;

import com.example.multidb.customer.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerMessage {
    @JmsListener(destination = "topicName", containerFactory = "jmsListenerContainerFactory")
    public void getMessageFromTopic(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.readValue(message, Customer.class);
        System.out.println("Message from topic :"+customer.getName() +" -  "+customer.getAddress());
    }
}