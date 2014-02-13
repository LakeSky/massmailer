/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataStructure;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import com.pepaproch.massmailmailer.poi.impl.XLSProcessor;
import com.pepaproch.massmailmailer.poi.impl.XSSRowToSrcRowMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
    private DataSourceValidator dataSourceValidator;

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

    /**
     *
     * @param dataSourceId
     * @param page
     * @param limit
     * @param sort
     * @return
     */
    @RequestMapping(value = "/{dataSourceId}/{page}/{limit}/{sort}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<DataSourceRow> showDataSourceData(@PathVariable("dataSourceId") String dataSourceId, @PathVariable("page") Integer page, @PathVariable("limit") Integer limit, @PathVariable("sort") String sort) {
        Sort sortable = new Sort(Sort.Direction.ASC, "id");
        Pageable pageSpecification = new PageRequest(page, limit, sortable);
        return dataSourceRowsRep.findByDataSourceIdPaginated(dataSourceId, pageSpecification);
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

    @RequestMapping(value = "/{dataSourceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteDataSource(@PathVariable("dataSourceId") String dataSourceId) {
        dataSourceRowsRep.delete(dataSourceRowsRep.findByDataSourceId(dataSourceId));
        dataRepository.delete(dataSourceId);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    private ResponseEntity saveDataSource(DataSource dataSource, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();

            ResponseEntity<List<FieldError>> errorResponse = new ResponseEntity<List<FieldError>>(fieldErrors, HttpStatus.UNPROCESSABLE_ENTITY);
            return errorResponse;
        } else {
            DataSource savedDataSource = dataRepository.save(dataSource);

            if (dataSource.getFileUploaded()) {
                updateData(savedDataSource);
            }

            ResponseEntity<DataSource> responseEntity = new ResponseEntity(savedDataSource, HttpStatus.CREATED);
            return responseEntity;
        }
    }

    private void updateData(DataSource dataSource) {
        dataSourceRowsRep.delete(dataSourceRowsRep.findByDataSourceId(dataSource.getId()));
        PoiFlatFileHandler processor = new XLSProcessor(new XSSRowToSrcRowMapper());
        RowMapper<RowRecords> rowMapper = processor.process(new File("/tmp/" + dataSource.getDataStructure().getFileName()));
        Collection<DataSourceRow> previewRows = new ArrayList();
        int i = 0;
        for (RowRecords row : rowMapper) {

            previewRows.add(new DataSourceRow(dataSource.getId(), row));
            i++;

        }
        dataSourceRowsRep.save(previewRows);
        dataSource.setRecordsCount(i);
        dataRepository.save(dataSource);

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
