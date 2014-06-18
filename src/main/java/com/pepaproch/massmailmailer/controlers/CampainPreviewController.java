/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.entity.Campain;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/campainpreview")
public class CampainPreviewController {

    @Autowired
    private CampainService campainService;
    @Autowired
    private CampainValidator campainValidator;

    @Autowired
    private CampainCreateService campainSendService;



    @RequestMapping(value = "/emailbody/{campainId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Campain getCampainEmailBody(@PathVariable("campainId") Long campainId) {
        return getCampainService().findOne(campainId);
    }


    public CampainPreviewController() {
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(getCampainValidator());

    }

    /**
     * @return the campainValidator
     */
    public CampainValidator getCampainValidator() {
        return campainValidator;
    }

    /**
     * @param campainValidator the campainValidator to set
     */
    public void setCampainValidator(CampainValidator campainValidator) {
        this.campainValidator = campainValidator;
    }

    /**
     * @return the campainService
     */
    public CampainService getCampainService() {
        return campainService;
    }

    /**
     * @param campainService the campainService to set
     */
    public void setCampainService(CampainService campainService) {
        this.campainService = campainService;
    }

    /**
     * @return the campainSendService
     */
    public CampainCreateService getCampainSendService() {
        return campainSendService;
    }

    /**
     * @param campainSendService the campainSendService to set
     */
    public void setCampainSendService(CampainCreateService campainSendService) {
        this.campainSendService = campainSendService;
    }
}
