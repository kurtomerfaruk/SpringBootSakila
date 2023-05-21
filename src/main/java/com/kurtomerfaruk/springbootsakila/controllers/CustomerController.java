package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.Customer;
import com.kurtomerfaruk.springbootsakila.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2023 09:36
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseController{

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        logger.info("> createCustomer");
        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        logger.info("> getAllCustomer");
        return customerService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer customerId){
        logger.info("> getCustomerById");
        return customerService.getId(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer customerId, @RequestBody Customer customer){
        logger.info("> updateCustomer");
        return customerService.getId(customerId)
                .map(savedCustomer -> {

                    savedCustomer.setFirstName(customer.getFirstName());
                    savedCustomer.setLastName(customer.getLastName());
                    savedCustomer.setEmail(customer.getEmail());
                    savedCustomer.setActive(customer.isActive());
                    savedCustomer.setCreateDate(customer.getCreateDate());
                    savedCustomer.setLastUpdate(customer.getLastUpdate());

                    Customer updatedCustomer = customerService.update(savedCustomer);
                    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer customerId){
        logger.info("> deleteCustomer");
        customerService.delete(customerId);
        logger.info("> deleted Customer");
        return new ResponseEntity<String>("Customer deleted successfully!.", HttpStatus.OK);

    }

}
