package com.codegym.controller;


import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("api/customers")
    public ResponseEntity<List<Customer>> listAllCustomer(){
        List<Customer> customerList = (List<Customer>) customerService.findAll();
        if(customerList.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
    }

    @PostMapping("api/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
          Customer customer1 = customerService.save(customer);
        return new ResponseEntity<Customer>(customer1,HttpStatus.CREATED);
    }

    @PutMapping("api/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id ,@RequestBody Customer customer){
        Customer currentCustomer = customerService.findById(id);
        if(currentCustomer == null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        currentCustomer.setName(customer.getName());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setPhone(customer.getPhone());

        customerService.save(currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer,HttpStatus.OK);
    }


    @DeleteMapping("api/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
        Customer customer1 = customerService.findById(id);

        if(customer1 == null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        customerService.remove(id);

        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
}
