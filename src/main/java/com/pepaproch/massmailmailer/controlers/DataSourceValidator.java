/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataStructureTypeVisitor;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.poi.DataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author pepa
 */
@Component
public class DataSourceValidator implements Validator {

    @Autowired
    private Validator validator;
    @Autowired
    private DataSourceInfoRep dataSourceInfoRep;

    @Override
    public boolean supports(Class<?> clazz) {
        return DataSource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        getValidator().validate(target, errors);
        if (target.getClass().isAssignableFrom(target.getClass())) {
            validate((DataSource) target, errors);
        }
    }

    private void validate(DataSource dataSource, Errors errors) {
        if (null != dataSource.getName()) {
            if (dataSourceInfoRep.findByName(dataSource.getName()).size() > 0) {
                if(dataSourceInfoRep.findByName(dataSource.getName()).size()==1 && (dataSourceInfoRep.findByName(dataSource.getName()).iterator().next().getId().compareTo(dataSource.getId())!=0))
                
                 errors.rejectValue("name", "error.Unique");
            }
            
            

        }
        DataStructureTypeVisitor v = new DataStructureTypeVisitor(DataType.EMAIL);
        dataSource.getDataStructure().visit(v);
        if(!v.hasType()) {
        errors.rejectValue("id","error.MustHaveEmail");
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

    /**
     * @return the dataSourceInfoRep
     */
    public DataSourceInfoRep getDataSourceInfoRep() {
        return dataSourceInfoRep;
    }

    /**
     * @param dataSourceInfoRep the dataSourceInfoRep to set
     */
    public void setDataSourceInfoRep(DataSourceInfoRep dataSourceInfoRep) {
        this.dataSourceInfoRep = dataSourceInfoRep;
    }

}
