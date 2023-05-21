package com.kurtomerfaruk.springbootsakila.services.impl;

import com.kurtomerfaruk.springbootsakila.models.Address;
import com.kurtomerfaruk.springbootsakila.services.AddressService;
import com.kurtomerfaruk.springbootsakila.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 19.05.2023 20:29
 */
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(Address entity) {
        return repository.save(entity);
    }

    @Override
    public List<Address> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Address> getId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Address update(Address entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
