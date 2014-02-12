/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    private DataSourceInfoRep dataRepository;
    @Autowired
    private DataSourceRowsRep dataSourceRowsRep;
     @Autowired
      private  DataSourceValidator dataSourceValidator;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody

    public List<DataSource> listDatasourcer() {
        return (List) dataRepository.findAll();
    }

    @RequestMapping(value = "/{dataSourceId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public DataSource showDataSource(@PathVariable("dataSourceId") String dataSourceId) {

        return dataRepository.findOne(dataSourceId);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveDatasourcer(@Valid @RequestBody DataSource dataSource, BindingResult result) {

        return saveDataSource(dataSource, result);

    }

    @RequestMapping(value = "/{dataSourceId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity updateDataSource(@Valid @RequestBody DataSource dataSource, BindingResult result) {
        return saveDataSource(dataSource, result);

    }

    @RequestMapping(value = "/{dataSourceId}",  method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteDataSource(@PathVariable("dataSourceId") String dataSourceId) {
        dataSourceRowsRep.delete(dataSourceRowsRep.findByDataSourceId(dataSourceId));
        dataRepository.delete(dataSourceId);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    private ResponseEntity saveDataSource(DataSource dataSource, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();

            ResponseEntity<List<ObjectError>> errorResponse = new ResponseEntity<List<ObjectError>>(allErrors, HttpStatus.UNPROCESSABLE_ENTITY);
            return errorResponse;
        } else {
            DataSource savedDataSource = dataRepository.save(dataSource);
            ResponseEntity<DataSource> responseEntity = new ResponseEntity(savedDataSource, HttpStatus.CREATED);
            return responseEntity;
        }
    }

    public DataSourceController() {
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(getDataSourceValidator());

    }

    /**
     * @return the dataSourceValidator
     */
    public DataSourceValidator getDataSourceValidator() {
        return dataSourceValidator;
    }

    /**
     * @param dataSourceValidator the dataSourceValidator to set
     */
    public void setDataSourceValidator(DataSourceValidator dataSourceValidator) {
        this.dataSourceValidator = dataSourceValidator;
    }

}
