/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataStructureMetaField;
import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.CampainAttachment;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.poi.DocumentFactory;
import com.pepaproch.massmailmailer.poi.DocumentFactoryImpl;
import com.pepaproch.massmailmailer.poi.PoiTypes;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
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
    
    @Autowired
    private CampainRepo campainRepo;

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
        
        Campain savedCampain = campainRepo.findOne(capmain.getId());
        if(null!=savedCampain && !savedCampain.getStatus().equalsIgnoreCase(Campain.STATUS_EDIT)) {
          errors.rejectValue("campainName", "error.AllredySent");
          return;
        }
        

        
        if (capmain.getCampainName() == null || "".equalsIgnoreCase(capmain.getCampainName())) {
            errors.rejectValue("campainName", "error.NotNull");
        }

        if (capmain.getSubject() == null || "".equalsIgnoreCase(capmain.getSubject())) {
            errors.rejectValue("subject", "error.NotNull");
        }
        if (capmain.getDataSourceId() == null) {
            errors.rejectValue("dataSourceId", "error.NotNull");
        } else {
            int i = 0;

            DataSource ds = dataSourceRep.findOne(capmain.getDataSourceId());
            DocumentFactory documentFactory = new DocumentFactoryImpl();
            if(null==capmain.getCampainAttachments()) {
            return;
            }
            for (CampainAttachment at : capmain.getCampainAttachments()) {
                Boolean validateTemplate = Boolean.TRUE;
                errors.setNestedPath("campainAttachments[" + i + "]");

                if (null != at.getAttachmentFileSystemName() && !"".equalsIgnoreCase(at.getAttachmentFileSystemName())) {

                    if (null == at.getAttachmentOutputName() || "".equalsIgnoreCase(at.getAttachmentOutputName())) {
                        errors.rejectValue("index", "error.attachmentOutputNameNotNull");
                        validateTemplate = Boolean.FALSE;
                    }
                    String extension = FilenameUtils.getExtension(at.getAttachmentName());
                    PoiTypes type = null;
                    try {
                        type = PoiTypes.valueOf(extension.toUpperCase());
                        if (!type.isTemplate()) {
                            validateTemplate = Boolean.FALSE;
                            if (at.getCustomizeAttachments()) {
                                errors.rejectValue("index", "error.cannotCustomizeNotSupportedType");
                            }
                        }
                    } catch (IllegalArgumentException ex) {

                    }

                    if (validateTemplate && at.getCustomizeAttachments()) {
                        if (null != type) {
                            DocumentHolder docu = documentFactory.getDocument("/tmp/" + at.getAttachmentFileSystemName(), new StringPlaceHolderHelper("###"));
                            List<String> dataFiledsNames = new ArrayList();
                            for (DataStructureMetaField f : ds.getDataStructure().getDataStructureFields()) {
                                dataFiledsNames.add("###" + f.getName() + "###");
                            }

                            if (at.getCustomizeAttachments() && !dataFiledsNames.containsAll(docu.getPlaceHolders())) {
                                errors.rejectValue("index", "error.NotAllWars");

                            }

                        } else {

                            errors.rejectValue("index", "error.cannotCustomizeUknownType");
                        }
                    }
                } else {
                    errors.rejectValue("index", "error.noFile");

                }

                i++;
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

    /**
     * @return the campainRepo
     */
    public CampainRepo getCampainRepo() {
        return campainRepo;
    }

    /**
     * @param campainRepo the campainRepo to set
     */
    public void setCampainRepo(CampainRepo campainRepo) {
        this.campainRepo = campainRepo;
    }

}
