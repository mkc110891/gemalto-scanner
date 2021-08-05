package com.interviewpractice.gateway.services;

import com.interviewpractice.gateway.entities.Customer;
import com.interviewpractice.gateway.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Created By Mayank Chauhan on 2/8/21 - 6:28 PM</p>
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer c) {
        return customerRepository.save(c);
    }

    @Override
    public List<Customer> saveAllCustomers(List<Customer> c) {
        return customerRepository.saveAll(c);
    }
}
