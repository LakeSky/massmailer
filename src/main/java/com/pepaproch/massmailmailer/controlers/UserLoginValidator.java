/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.controlers.forms.LoginForm;
import com.pepaproch.massmailmailer.security.UserLogin;
import com.pepaproch.massmailmailer.security.UserLoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author pepa
 */
@Component
public class UserLoginValidator implements Validator {

    @Autowired
    private Validator validator;
    @Autowired
    private UserLoginDao userLoginDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        getValidator().validate(target, errors);
        if (target.getClass().isAssignableFrom(UserLogin.class)) {
            validate((LoginForm) target, errors);
        }
    }

    private void validate(LoginForm userLogin, Errors errors) {
        if (null != userLogin.getUserName()) {
            UserLogin findByUserName = userLoginDao.findByUserName(userLogin.getUserName());
            if (findByUserName != null && !findByUserName.getId().equals(userLogin.getLoginId())) {
                errors.rejectValue("name", "error.Unique");
            }

        }

    }
    



    /**
     * @return the validator
     */
    public Validator getValidator() {
        return validator;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

}
