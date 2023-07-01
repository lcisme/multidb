package com.example.multidb.depart.controller;

import com.example.multidb.customer.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/jms")
public class JmsController {
    @Autowired
    private JmsTemplate jmsTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/send")
    public void sendMessage(@RequestBody Customer customer){
        try {
            jmsTemplate.convertAndSend("topicName", objectMapper.writeValueAsString(customer));
        } catch (Exception ex){
            System.out.printf(ex.getMessage());
        }
    }

    @PostMapping("/send/queue")
    public void sendQueueMessage(@RequestParam String name){
        jmsTemplate.convertAndSend("queueName", name);
    }
}
