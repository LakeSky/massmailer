/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Campain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author pepa
 */
@Component
public class CampainValidator implements Validator {

    @Autowired
    @Qualifier("validator")
    private Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Campain.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        getValidator().validate(target, errors);
        if (Campain.class.isAssignableFrom(target.getClass())) {
            validate((Campain) target, errors);
        }
    }

    private void validate(Campain capmain, Errors errors) {
        if (capmain.getDataSourceId() == null) {
            errors.rejectValue("dataSourceId", "error.NotNull");
        }

        if (capmain.getAttachmentName() == null) {
            errors.rejectValue("attachmentName", "error.NotNull");
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
