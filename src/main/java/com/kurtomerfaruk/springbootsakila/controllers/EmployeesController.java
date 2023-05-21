package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.Employees;
import com.kurtomerfaruk.springbootsakila.services.EmployeesService;
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
@RequestMapping("/api/employees")
public class EmployeesController  extends BaseController{

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employees createEmployees(@RequestBody Employees employees){
        logger.info("> createEmployees");
        return employeesService.save(employees);
    }

    @GetMapping
    public List<Employees> getAllEmployees(){
        logger.info("> getAllEmployees");
        return employeesService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employees> getEmployeesById(@PathVariable("id") Integer employeesId){
        logger.info("> getEmployeesById");
        return employeesService.getId(employeesId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Employees> updateEmployees(@PathVariable("id") Integer employeesId, @RequestBody Employees employees){
        logger.info("> updateEmployees");
        return employeesService.getId(employeesId)
                .map(savedEmployees -> {

                    savedEmployees.setFirstName(employees.getFirstName());
                    savedEmployees.setLastName(employees.getLastName());
                    savedEmployees.setLastUpdate(employees.getLastUpdate());

                    Employees updatedEmployees = employeesService.update(savedEmployees);
                    return new ResponseEntity<>(updatedEmployees, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployees(@PathVariable("id") Integer employeesId){
        logger.info("> deleteEmployees");
        employeesService.delete(employeesId);
        logger.info("> deleted Employees");
        return new ResponseEntity<String>("Employees deleted successfully!.", HttpStatus.OK);

    }

}
