package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.Address;
import com.kurtomerfaruk.springbootsakila.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 19.05.2023 20:24
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController  extends BaseController{

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody Address address){
        logger.info("> createAddress");
        return addressService.save(address);
    }

    @GetMapping
    public List<Address> getAllAddress(){
        logger.info("> getAllAddress");
        return addressService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Integer addressId){
        logger.info("> getAddressById");
        return addressService.getId(addressId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Integer addressId, @RequestBody Address address){
        logger.info("> updateAddress");
        return addressService.getId(addressId)
                .map(savedAddress -> {

                    savedAddress.setAddress(address.getAddress());
                    savedAddress.setAddress2(address.getAddress2());
                    savedAddress.setDistrict(address.getDistrict());
                    //savedAddress.setCity(address.getCity());
                    savedAddress.setPostalCode(address.getPostalCode());
                    savedAddress.setLastUpdate(address.getLastUpdate());

                    Address updatedAddress = addressService.update(savedAddress);
                    return new ResponseEntity<>(updatedAddress, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Integer addressId){
        logger.info("> deleteAddress");
        addressService.delete(addressId);
        logger.info("> deleted Address");
        return new ResponseEntity<String>("Address deleted successfully!.", HttpStatus.OK);

    }

}
