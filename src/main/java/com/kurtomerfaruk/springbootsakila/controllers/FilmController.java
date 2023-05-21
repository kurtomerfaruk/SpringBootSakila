package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.Film;
import com.kurtomerfaruk.springbootsakila.services.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 21.05.2023 09:37
 */
@RestController
@RequestMapping("/api/films")
public class FilmController extends BaseController{

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film createFilm(@RequestBody Film film){
        logger.info("> createFilm");
        return filmService.save(film);
    }

    @GetMapping
    public List<Film> getAllFilm(){
        logger.info("> getAllFilm");
        return filmService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable("id") Integer filmId){
        logger.info("> getFilmById");
        return filmService.getId(filmId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable("id") Integer filmId, @RequestBody Film film){
        logger.info("> updateFilm");
        return filmService.getId(filmId)
                .map(savedFilm -> {

                    savedFilm.setTitle(film.getTitle());
                    savedFilm.setDescription(film.getDescription());
                    savedFilm.setReleaseYear(film.getReleaseYear());
                    savedFilm.setRentalDuration(film.getRentalDuration());
                    savedFilm.setRentalRate(film.getRentalRate());
                    savedFilm.setLength(film.getLength());
                    savedFilm.setReplacementCost(film.getReplacementCost());
                    savedFilm.setRating(film.getRating());
                    savedFilm.setSpecialFeatures(film.getSpecialFeatures());
                    savedFilm.setLastUpdate(film.getLastUpdate());

                    Film updatedFilm = filmService.update(savedFilm);
                    return new ResponseEntity<>(updatedFilm, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable("id") Integer filmId){
        logger.info("> deleteFilm");
        filmService.delete(filmId);
        logger.info("> deleted Film");
        return new ResponseEntity<String>("Film deleted successfully!.", HttpStatus.OK);

    }

}
