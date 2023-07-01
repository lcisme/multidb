package com.example.multidb.dto;

import com.example.multidb.customer.entity.Customer;
import lombok.Data;

@Data
public class PhoneDTO {
    private Long id;
    private String phoneNumber;
    private CustomerDTO customer;
}
