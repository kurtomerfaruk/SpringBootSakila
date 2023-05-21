package com.kurtomerfaruk.springbootsakila.services.impl;

import com.kurtomerfaruk.springbootsakila.models.Employees;
import com.kurtomerfaruk.springbootsakila.repositories.EmployeesRepository;
import com.kurtomerfaruk.springbootsakila.services.EmployeesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2023 10:09
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {

    private EmployeesRepository employeesRepository;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Employees save(Employees entity) {
        return employeesRepository.save(entity);
    }

    @Override
    public List<Employees> getAll() {
        return employeesRepository.findAll();
    }

    @Override
    public Optional<Employees> getId(Integer id) {
        return employeesRepository.findById(id);
    }

    @Override
    public Employees update(Employees entity) {
        return employeesRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        employeesRepository.deleteById(id);
    }
}
