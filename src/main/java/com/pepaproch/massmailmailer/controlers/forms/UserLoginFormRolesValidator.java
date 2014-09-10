/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers.forms;

import org.springframework.validation.Errors;

/**
 *
 * @author pepa
 */
public class UserLoginFormRolesValidator implements ValidatorVisitor<LoginForm> {

    private final Errors errors;

    /**
     *
     * @param e
     */
    public UserLoginFormRolesValidator(Errors e) {
        this.errors = e;
    }

    @Override
    public void visit(LoginForm t) {
        if (t.getRoles() != null) {
            int i = 0;
            for (String s : t.getRoles()) {
                errors.setNestedPath("roles[" + i + "]");
                if(i==1) {
                getErrors().rejectValue(null, "NotEmptu");
                }
                i++;
            }
        }

    }

    /**
     * @return the errors
     */
    public Errors getErrors() {
        return errors;
    }

}
