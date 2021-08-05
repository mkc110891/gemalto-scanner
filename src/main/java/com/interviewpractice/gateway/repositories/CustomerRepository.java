package com.interviewpractice.gateway.repositories;

import com.interviewpractice.gateway.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Created By Mayank Chauhan on 2/8/21 - 4:26 PM</p>
 */
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, String> {
    List<Customer> findByFirstName(String firstName);
}
