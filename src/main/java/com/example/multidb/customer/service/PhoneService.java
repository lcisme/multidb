package com.example.multidb.customer.service;

import com.example.multidb.customer.entity.Customer;
import com.example.multidb.customer.entity.Phone;
import com.example.multidb.customer.repository.CustomerRepository;
import com.example.multidb.customer.repository.PhoneRepository;
import com.example.multidb.dto.PhoneDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    CustomerRepository customerRepository;

    private ModelMapper modelMapper;

    @PostConstruct
    public void createInstance(){
        modelMapper = new ModelMapper();
    }

    public void createPhone(PhoneDTO phoneDTO){
        Customer customer = customerRepository.findById(phoneDTO.getCustomer().getId()).get();
        if (customer != null){
            Phone phone = modelMapper.map(phoneDTO, Phone.class);
            phone.setCustomer(customer);
            phoneRepository.save(phone);
        }
    }
}
