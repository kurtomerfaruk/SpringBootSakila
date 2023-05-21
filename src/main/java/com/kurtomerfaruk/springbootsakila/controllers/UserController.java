package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.User;
import com.kurtomerfaruk.springbootsakila.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 20.05.2023 23:16
 */
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController{

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        logger.info("> createUser");
        return userService.save(user);
    }

    @GetMapping
    public List<User> getAllUser(){
        logger.info("> getAllUser");
        return userService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId){
        logger.info("> getUserById");
        return userService.getId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer userId, @RequestBody User user){
        logger.info("> updateUser");
        return userService.getId(userId)
                .map(savedUser -> {

                    savedUser.setUsername(user.getUsername());
                    savedUser.setPassword(user.getPassword());

                    User updatedUser = userService.update(savedUser);
                    return new ResponseEntity<>(updatedUser, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId){
        logger.info("> deleteUser");
        userService.delete(userId);
        logger.info("> deleted User");
        return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);

    }
}
