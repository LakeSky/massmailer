/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.controlers.forms.LoginForm;
import com.pepaproch.massmailmailer.security.MaillerUserService;
import com.pepaproch.massmailmailer.security.UserLogin;
import com.pepaproch.massmailmailer.security.UserLoginDao;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    @Qualifier("maillerUserService")
    private MaillerUserService maiilerserService;

    @Autowired
    private UserLoginValidator userLoginValidator;

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public LoginForm getLogin(@PathVariable("userId") Long userId) {
        UserLogin login = getUserLogindao().getByUserInfoId(userId);
        if (login != null) {
            return new LoginForm(login);
        } else {
            return new LoginForm(userId);
        }
    }

    /**
     *
     * @param loginForm
     * @param result
     * @return
     */
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveLogin(@Valid @RequestBody LoginForm loginForm, BindingResult result) {
        ResponseEntity responseEntity;
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            ResponseEntity<List<FieldError>> errorResponse = new ResponseEntity<>(fieldErrors, HttpStatus.UNPROCESSABLE_ENTITY);
            return errorResponse;
            
        } else {
            responseEntity = new ResponseEntity(maiilerserService.saveLoginForm(loginForm), HttpStatus.CREATED);
            return responseEntity;
        }

    }

    @RequestMapping(value = "changepassword", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity changePassword(@RequestParam("userId") Long userId, @RequestParam("password") String password) {
        UserLogin userLogin = getUserLogindao().getByUserInfoId(userId);
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

    /**
     * @return the maiilerserService
     */
    public MaillerUserService getMaiilerserService() {
        return maiilerserService;
    }

    /**
     * @param maiilerserService the maiilerserService to set
     */
    public void setMaiilerserService(MaillerUserService maiilerserService) {
        this.maiilerserService = maiilerserService;
    }

}
