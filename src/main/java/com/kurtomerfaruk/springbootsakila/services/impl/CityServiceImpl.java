package com.kurtomerfaruk.springbootsakila.services.impl;

import com.kurtomerfaruk.springbootsakila.models.City;
import com.kurtomerfaruk.springbootsakila.services.CityService;
import com.kurtomerfaruk.springbootsakila.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2023 09:46
 */
@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City save(City entity) {
        return cityRepository.save(entity);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> getId(Integer id) {
        return cityRepository.findById(id);
    }

    @Override
    public City update(City entity) {
        return cityRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }
}
