package com.example.multidb.customer.controller;

import com.example.multidb.annotation.LoggingAnnotation;
import com.example.multidb.customer.entity.Customer;
import com.example.multidb.customer.projection.CustomerProjection;
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

    @PostMapping("/create")
    @LoggingAnnotation
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping("getAll")
    public List<Customer> getAllCustomer() {
        return customerService.findAll();
    }

    @GetMapping("filterCustomer")
    public List<Customer> filterCustomer(@RequestParam(name = "name", required = false) String name, @RequestParam(required = false) String address, @RequestParam(required = false) Integer age) {
        return customerService.findCustomerByNameAndAddressAndAge(name, address, age);
    }

    @GetMapping("/findByPhoneAndName")
    public List<Customer> findByPhoneAndName(@RequestParam(required = false) String name, @RequestParam(required = false) String phoneNumber) {
        return customerService.findByPhoneAndName(name, phoneNumber);
    }

    @GetMapping("/multiSelect")
    public List<Object[]> multiSelect(@RequestParam(required = false) String name) {
        return customerService.multiSelect(name);
    }

    @GetMapping("/findByPhoneNumber")
    public List<Customer> findByPhone(@RequestParam(required = false) String phoneNumber){
        return customerService.findByPhone(phoneNumber);
    }

    @GetMapping("/info")
    public List<CustomerProjection> customerInfo() {
        return customerService.getCustomerInfo();
    }

    @GetMapping("/findByName")
    public List<Customer> findByName(@RequestParam(required = false) String name){
        return customerService.findByName(name);
    }
}