package com.interviewpractice.gateway.controllers;

import com.github.javafaker.Faker;
import com.interviewpractice.gateway.entities.Customer;
import com.interviewpractice.gateway.models.CustomerModel;
import com.interviewpractice.gateway.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created By Mayank Chauhan on 2/8/21 - 4:29 PM</p>
 */
@RestController
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    private Faker faker = new Faker();

    @GetMapping("/bulk-create")
    public String bulkCreate() {
        List<Customer> customers = new ArrayList<>(5);
        for(int i = 0; i < 5; i++) {
            Customer c = new Customer();
            c.setFirstName(faker.name().firstName());
            c.setLastName(faker.name().lastName());
            customers.add(c);
        }
        customerService.saveAllCustomers(customers);
        return "Customer created successfully";
    }

    @PostMapping("/store")
    public ResponseEntity<String> store(@Valid @RequestBody CustomerModel customer, Errors errors) {
        if(errors.hasErrors()) {
            new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        } else {
            try {
                Customer c = new Customer();
                c.setFirstName(customer.getFirstName());
                c.setLastName(customer.getLastName());
                customerService.saveCustomer(c);
                return ResponseEntity.ok("Customer stored successfully");
            } catch (Exception e) {
                log.error("Error in customer creation: " + e);
                return ResponseEntity.ok("Error in customer creation");
            }
        }
        return ResponseEntity.ok("Customer stored successfully");
    }
}
