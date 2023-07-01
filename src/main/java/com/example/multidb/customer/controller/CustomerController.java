package com.example.multidb.customer.controller;

import com.example.multidb.annotation.LoggingAnnotation;
import com.example.multidb.customer.entity.Customer;
import com.example.multidb.customer.repository.CustomerRepository;
import com.example.multidb.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    @LoggingAnnotation
    public void createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
    }

    @GetMapping("getAll")
    public List<Customer> getAllCustomer(){
        return customerService.findAll();
    }

    @GetMapping("filterCustomer")
    public List<Customer> filterCustomer(@RequestParam(name = "name",required = false) String name, @RequestParam(required = false) String address, @RequestParam(required = false) Integer age){
//        return customerService.findByNameAndAddressAndAge(name,address,age);
        return customerService.findCustomerByNameAndAddressAndAge(name,address,age);
    }


}
