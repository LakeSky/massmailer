/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.DataSource;
import com.pepaproch.massmailmailer.repository.DataSourceRep;
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
@RequestMapping("/datasource")
public class DataSourceController {

    @Autowired
    private DataSourceRep dataRepository;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<DataSource> listDatasourcer() {
        return (List) dataRepository.findAll();
    }

    @RequestMapping(value = "/{dataSourceId}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public DataSource showDataSourcer(@PathVariable("dataSourceId") Integer dataSourceId) {
       
        return dataRepository.findOne(dataSourceId);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveDatasourcer(@Valid @RequestBody DataSource dataSource, BindingResult result) {

        return saveDataSource(dataSource,result);

    }

    @RequestMapping(value = "/{dataSourcerId}",consumes = MediaType.APPLICATION_JSON_VALUE,method = { RequestMethod.PUT,RequestMethod.POST} )
    @ResponseBody
    public ResponseEntity updateDataSourcer(@Valid @RequestBody DataSource dataSource, BindingResult result) {

        return saveDataSource(dataSource,result);

    }
    
    private ResponseEntity saveDataSource(DataSource dataSource,BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<FieldError> fieldErrors = result.getFieldErrors();
            ResponseEntity<List<FieldError>> errorResponse = new ResponseEntity<List<FieldError>>(fieldErrors, HttpStatus.UNPROCESSABLE_ENTITY);
            return errorResponse;
        } else {
            DataSource savedDataSource = dataRepository.save(dataSource);
            ResponseEntity<DataSource> responseEntity = new ResponseEntity(savedDataSource, HttpStatus.CREATED);
            return responseEntity;
        }
    }

    public DataSourceController() {
    }



}