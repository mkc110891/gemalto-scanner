package com.interviewpractice.gateway.services;

import com.interviewpractice.gateway.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Created By Mayank Chauhan on 2/8/21 - 6:27 PM</p>
 */
public interface CustomerService {
    public Customer saveCustomer(Customer c);
    public List<Customer> saveAllCustomers(List<Customer> c);
}
