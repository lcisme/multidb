package com.example.multidb.customer.repository;

import com.example.multidb.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameAndAddressAndAge(String name, String address, Integer age);
}

