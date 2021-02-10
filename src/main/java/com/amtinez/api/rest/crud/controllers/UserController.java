package com.amtinez.api.rest.crud.controllers;

import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.facades.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author amartinezcerro@gmail.com
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userFacade.findAllUsers());
    }

    @PutMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody final User user) {
        return ResponseEntity.ok(userFacade.registerUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable final Long id) {
        final Optional<User> user = userFacade.findUser(id);
        return user.map(userFound -> ResponseEntity.ok().body(userFound)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        final Optional<User> user = userFacade.findUser(id);
        if (user.isPresent()) {
            userFacade.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
