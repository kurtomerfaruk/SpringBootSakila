package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.Country;
import com.kurtomerfaruk.springbootsakila.services.CountryService;
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
@RequestMapping("/api/countries")
public class CountryController extends BaseController{

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country createCountry(@RequestBody Country country){
        logger.info("> createCountry");
        return countryService.save(country);
    }

    @GetMapping
    public List<Country> getAllCountry(){
        logger.info("> getAllCountry");
        return countryService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") Integer countryId){
        logger.info("> getCountryById");
        return countryService.getId(countryId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Integer countryId, @RequestBody Country country){
        logger.info("> updateCountry");
        return countryService.getId(countryId)
                .map(savedCountry -> {

                    savedCountry.setCountry(country.getCountry());
                    savedCountry.setLastUpdate(country.getLastUpdate());

                    Country updatedCountry = countryService.update(savedCountry);
                    return new ResponseEntity<>(updatedCountry, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") Integer countryId){
        logger.info("> deleteCountry");
        countryService.delete(countryId);
        logger.info("> deleted Country");
        return new ResponseEntity<String>("Country deleted successfully!.", HttpStatus.OK);

    }
}
