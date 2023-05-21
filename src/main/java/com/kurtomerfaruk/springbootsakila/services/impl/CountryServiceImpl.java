package com.kurtomerfaruk.springbootsakila.services.impl;

import com.kurtomerfaruk.springbootsakila.models.Country;
import com.kurtomerfaruk.springbootsakila.services.CountryService;
import com.kurtomerfaruk.springbootsakila.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2023 09:50
 */
@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country save(Country entity) {
        return countryRepository.save(entity);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getId(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country update(Country entity) {
        return countryRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }
}
