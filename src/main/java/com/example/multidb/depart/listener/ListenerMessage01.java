package com.example.multidb.depart.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerMessage01 {
    @JmsListener(destination =  "topicName", containerFactory = "jmsListenerContainerFactory")
    public void getMessageFromTopic(String messagge){
        System.out.println("Message from topic01: "+messagge);
    }
}