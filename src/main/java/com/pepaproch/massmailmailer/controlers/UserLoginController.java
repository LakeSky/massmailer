/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.controlers.forms.LoginForm;
import com.pepaproch.massmailmailer.security.UserLogin;
import com.pepaproch.massmailmailer.security.UserLoginDao;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private UserLoginDao userLogindao;

    @Autowired
    private UserLoginValidator userLoginValidator;

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public LoginForm getLogin(@PathVariable("userId") Long userId) {
        UserLogin login = getUserLogindao().getByUserInfoId(userId);
        return new LoginForm(login);
    }

    /**
     *
     * @param loginForm
     * @param result
     * @return
     */
    @RequestMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public LoginForm saveLogin(@Valid @RequestBody LoginForm loginForm, BindingResult result) {
       
        return new LoginForm();
    }
    
    
    
    @RequestMapping(value= "changepassword" , consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity changePassword(@RequestParam("userId") Long userId, @RequestParam("password") String password) {
        UserLogin userLogin = userLogindao.getByUserInfoId(userId);
        //  UserLogin savedLogin = getUserLogindao().save(login);
        ResponseEntity<UserLogin> responseEntity = new ResponseEntity(Boolean.TRUE, HttpStatus.CREATED);
        return responseEntity;

    }
    
    
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity roles() {
        ResponseEntity<Collection<? extends GrantedAuthority>> responseEntity = new ResponseEntity(AppContext.getUserDetails().getAuthorities(), HttpStatus.ACCEPTED);
        return responseEntity;

    }
    

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(getUserLoginValidator());

    }

    public UserLoginController() {
    }

    /**
     * @return the userLogindao
     */
    public UserLoginDao getUserLogindao() {
        return userLogindao;
    }

    /**
     * @param userLogindao the userLogindao to set
     */
    public void setUserLogindao(UserLoginDao userLogindao) {
        this.userLogindao = userLogindao;
    }

    /**
     * @return the userLoginValidator
     */
    public UserLoginValidator getUserLoginValidator() {
        return userLoginValidator;
    }

    /**
     * @param userLoginValidator the userLoginValidator to set
     */
    public void setUserLoginValidator(UserLoginValidator userLoginValidator) {
        this.userLoginValidator = userLoginValidator;
    }

}
