/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Users;
import com.pepaproch.massmailmailer.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<Users> listUsers() {
        return (List) userService.listUsers();
    }

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public Users showUser(@PathVariable("userId") BigDecimal userId) {
        Users user = userService.getUserR().findOne(userId);
        return user;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveUsers(@Valid @RequestBody Users user, BindingResult result) {

        return saveUser(user,result);

    }

    @RequestMapping(value = "/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE,method = { RequestMethod.PUT,RequestMethod.POST} )
    @ResponseBody
    public ResponseEntity updateeUsers(@Valid @RequestBody Users user, BindingResult result) {

        return saveUser(user,result);

    }
    
    private ResponseEntity saveUser(Users user,BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<FieldError> fieldErrors = result.getFieldErrors();
            ResponseEntity<List<FieldError>> errorResponse = new ResponseEntity<List<FieldError>>(fieldErrors, HttpStatus.UNPROCESSABLE_ENTITY);
            return errorResponse;
        } else {
            Users savedUser = userService.getUserR().save(user);
            ResponseEntity<Users> responseEntity = new ResponseEntity(savedUser, HttpStatus.CREATED);
            return responseEntity;
        }
    }

    public UsersController() {
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
