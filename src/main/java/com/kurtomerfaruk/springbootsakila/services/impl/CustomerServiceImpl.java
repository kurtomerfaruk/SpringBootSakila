package com.kurtomerfaruk.springbootsakila.services.impl;

import com.kurtomerfaruk.springbootsakila.models.Customer;
import com.kurtomerfaruk.springbootsakila.repositories.CustomerRepository;
import com.kurtomerfaruk.springbootsakila.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2023 10:03
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getId(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer update(Customer entity) {
        return customerRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
