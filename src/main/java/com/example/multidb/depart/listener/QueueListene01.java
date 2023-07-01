package com.example.multidb.depart.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class QueueListene01 {
    @JmsListener(destination = "queueName", containerFactory = "jmsListenerContainerFactory")
    public void getMessageFromTopic(String message){
        System.out.println("Message from queue listener 01 :"+message);
    }
}