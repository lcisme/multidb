package com.example.multidb.customer.service;

import com.example.multidb.customer.entity.Customer;
import com.example.multidb.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    @Qualifier("customerConfigEntityManager")
    private EntityManager em;

    @Transactional
    @Qualifier("customerConfigTransactionManager")
    public void createCustomer(Customer customer){
        customerRepository.save(customer);

    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public List<Customer> findByNameAndAddressAndAge(String name, String address, Integer age){
        return customerRepository.findByNameAndAddressAndAge(name, address, age);
    }

    public List<Customer> findCustomerByNameAndAddressAndAge(String name, String address, Integer age){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> customer = cq.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();
        if (name != null){
            predicates.add(cb.equal(customer.get("name"), name));
        }
        if (address != null){
            predicates.add(cb.like(customer.get("address"),"%"+address+"%"));
        }
        if (age != null){
            predicates.add(cb.gt(customer.get("age"), age));
        }
        cq.where(predicates.toArray((new Predicate[0])));
        return em.createQuery(cq).getResultList();
    }


}
