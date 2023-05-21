package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.City;
import com.kurtomerfaruk.springbootsakila.services.CityService;
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
@RequestMapping("/api/cities")
public class CityController extends BaseController{

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@RequestBody City city){
        logger.info("> createCity");
        return cityService.save(city);
    }

    @GetMapping
    public List<City> getAllCity(){
        logger.info("> getAllCity");
        return cityService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Integer cityId){
        logger.info("> getCityById");
        return cityService.getId(cityId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<City> updateCity(@PathVariable("id") Integer cityId, @RequestBody City city){
        logger.info("> updateCity");
        return cityService.getId(cityId)
                .map(savedCity -> {

                    savedCity.setCity(city.getCity());
                    savedCity.setLastUpdate(city.getLastUpdate());

                    City updatedCity = cityService.update(savedCity);
                    return new ResponseEntity<>(updatedCity, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") Integer cityId){
        logger.info("> deleteCity");
        cityService.delete(cityId);
        logger.info("> deleted City");
        return new ResponseEntity<String>("City deleted successfully!.", HttpStatus.OK);

    }

}
