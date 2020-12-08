package com.amtinez.api.rest.crud.controllers;

import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.facades.UserFacade;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import static com.amtinez.api.rest.crud.constants.ControllerConstants.User.BASE_URL;
import static com.amtinez.api.rest.crud.constants.ControllerConstants.User.ID_URL;

/**
 * @author amartinezcerro@gmail.com
 */
@RestController
@RequestMapping(BASE_URL)
public class UserController {

    //TODO: IMPLEMENT EXCEPTIONS AND MESSAGES FOR DIFFERENT EVENTS, E.G. USER NOT FOUND

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public List<User> findAllUsers() {
        return userFacade.findAllUsers();
    }

    @PutMapping
    public User registerUser(@RequestBody final User user) {
        return userFacade.registerUser(user);
    }

    @GetMapping(ID_URL)
    public User findUser(@PathVariable final Long id) throws Exception {
        return userFacade.findUser(id).orElseThrow(Exception::new);
    }

    @DeleteMapping(ID_URL)
    public void deleteUser(@PathVariable final Long id) {
        userFacade.deleteUser(id);
    }

}
