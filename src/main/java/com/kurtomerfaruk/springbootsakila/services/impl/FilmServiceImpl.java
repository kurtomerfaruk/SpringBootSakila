package com.kurtomerfaruk.springbootsakila.services.impl;

import com.kurtomerfaruk.springbootsakila.models.Film;
import com.kurtomerfaruk.springbootsakila.repositories.FilmRepository;
import com.kurtomerfaruk.springbootsakila.services.FilmService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2023 10:19
 */
@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film save(Film entity) {
        return filmRepository.save(entity);
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Optional<Film> getId(Integer id) {
        return filmRepository.findById(id);
    }

    @Override
    public Film update(Film entity) {
        return filmRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        filmRepository.deleteById(id);
    }
}
