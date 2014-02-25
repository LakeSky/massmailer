/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataStructureMetaField;
import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.PlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.WordDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.validator.internal.util.CollectionHelper;
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

    @Autowired
    private DataSourceInfoRep dataSourceRep;

    @Override
    public boolean supports(Class<?> clazz) {
        return Campain.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        getValidator().validate(target, errors);
        if (Campain.class.isAssignableFrom(target.getClass())) {
            try {
                validate((Campain) target, errors);
            } catch (IOException ex) {
                Logger.getLogger(CampainValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void validate(Campain capmain, Errors errors) throws IOException {
        if (capmain.getDataSourceId() == null) {
            errors.rejectValue("dataSourceId", "error.NotNull");
        } else {
            DataSource ds = dataSourceRep.findOne(capmain.getDataSourceId());
            PlaceHolderHelper pl = new StringPlaceHolderHelper("####");

            DocumentHolder docu = new WordDocument("/tmp/" + capmain.getAttachmentName(), pl);

            List<String> dataFiledsNames = new ArrayList();
            for (DataStructureMetaField f : ds.getDataStructure().getDataStructureFields()) {
                dataFiledsNames.add(f.getName());
            }
            

            if (dataFiledsNames.containsAll(docu.getPlaceHolders())) {
                System.out.println("OK");

            } else {
                errors.rejectValue("attachmentName", "error.NotAllWars");
            }

            if (capmain.getCampainName() == null || "".equalsIgnoreCase(capmain.getCampainName())) {
                errors.rejectValue("campainName", "error.NotNull");
            }

            if (capmain.getCustomizeAttachments() && capmain.getAttachmentName() == null) {
                errors.rejectValue("attachmentName", "error.NotNull");
            }
            if (capmain.getSubject() == null || "".equalsIgnoreCase(capmain.getSubject())) {
                errors.rejectValue("subject", "error.NotNull");
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

    /**
     * @return the dataSourceRep
     */
    public DataSourceInfoRep getDataSourceRep() {
        return dataSourceRep;
    }

    /**
     * @param dataSourceRep the dataSourceRep to set
     */
    public void setDataSourceRep(DataSourceInfoRep dataSourceRep) {
        this.dataSourceRep = dataSourceRep;
    }

}
