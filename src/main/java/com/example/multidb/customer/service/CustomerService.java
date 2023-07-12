package com.example.multidb.customer.service;

import com.example.multidb.customer.entity.Customer;
import com.example.multidb.customer.entity.Phone;
import com.example.multidb.customer.projection.CustomerProjection;
import com.example.multidb.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
        return (List<Customer>) customerRepository.findAll();
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
            cq.orderBy(cb.desc(customer.get("age")));
        }
        cq.where(predicates.toArray((new Predicate[0])));
        return em.createQuery(cq).getResultList();
    }

    public List<Customer> findByPhoneAndName(String name, String phone){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> customer = cq.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();
        Join<Customer, Phone> join = customer.join("phones", JoinType.INNER);
        if (name != null){
            predicates.add(cb.equal(customer.get("name"), name));
        }
        if (phone != null){
            predicates.add(cb.equal(join.get("phoneNumber"), phone));
        }

        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(customer.get("name")));
        orders.add(cb.desc(customer.get("age")));
        cq.where(predicates.toArray(new Predicate[0])).orderBy(orders).distinct(false);
        TypedQuery<Customer> query = em.createQuery(cq).setFirstResult(0).setMaxResults(3);
        return query.getResultList();
    }

    public List<Object[]> multiSelect(String name){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Customer> customer = cq.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();
        if (name != null){
            predicates.add(cb.like(customer.get("name"), "%" +name+ "%"));
        }
        cq.multiselect(customer.get("name"), cb.count(customer)).groupBy(customer.get("name"));
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

    public List<Customer> findByPhone(String phone){
        return customerRepository.findByPhoneNumber(phone);
    }

    public List<CustomerProjection> getCustomerInfo() {
        return customerRepository.getCustomerInfo();
    }

    public List<Customer> findByName(String name){
       Pageable pageable = PageRequest.of(0,3,Sort.by("name").and(Sort.by("age")));
       return customerRepository.findByName(name,pageable);
    }

}
