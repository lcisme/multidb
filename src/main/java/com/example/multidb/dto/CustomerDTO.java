package com.example.multidb.dto;

import com.example.multidb.customer.entity.Phone;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private Integer age;
    private String address;
    List<PhoneDTO> phones;

}
