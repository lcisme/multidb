package com.example.multidb.customer.repository;

import com.example.multidb.customer.entity.Customer;
import com.example.multidb.customer.projection.CustomerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> findByNameAndAddressAndAge(String name, String address, Integer age);

    @Query(value = "select c from Customer c join c.phones phones where phones.phoneNumber =:phoneNumber")
    List<Customer> findByPhoneNumber(String phoneNumber);

    @Query(value = " select c.name, p.phone_number as phoneNumber from customer c inner join phone p on c.id = p.customer_id ", nativeQuery = true)
    List<CustomerProjection> getCustomerInfo();

    List<Customer> findByName(String name, Pageable pageable);
}

