package com.example.multidb.customer.controller;

import com.example.multidb.annotation.LoggingAnnotation;
import com.example.multidb.customer.entity.Customer;
import com.example.multidb.customer.entity.Phone;
import com.example.multidb.customer.repository.PhoneRepository;
import com.example.multidb.customer.service.PhoneService;
import com.example.multidb.dto.PhoneDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;


    @PostMapping
    public void createPhone(@RequestBody PhoneDTO phoneDTO){
        phoneService.createPhone(phoneDTO);
    }

}
