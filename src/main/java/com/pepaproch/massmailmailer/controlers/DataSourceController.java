/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/datasource")
public class DataSourceController {

    @Autowired
    private DataSourceInfoRep dataRepository;
    @Autowired
    private DataSourceRowsRep dataSourceRowsRep;
    @Autowired
    private DataSourceValidator dataSourceValidator;
    @Autowired
    private DataSourceRowService dataSourceRowService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<DataSource> listDatasourcer(@RequestParam(value = "search", required = false) String searchColumn, @RequestParam(value = "searchString", required = false) String searchString) {
        if ("name".equalsIgnoreCase(searchColumn)) {
            return (List) dataRepository.findByLikName(".*" + searchString + ".*");
        } else {
            return (List) dataRepository.findAll();
        }
    }

    @RequestMapping(value = "/{dataSourceId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public DataSource showDataSource(@PathVariable("dataSourceId") String dataSourceId) {
        DataSource findOne = dataRepository.findOne(dataSourceId);
        findOne.setFileUploaded(Boolean.FALSE);
        return findOne;
    }

    /**
     * datasource/:dataSourceId/rows/:page/:limit/:sort/:sortDir/:search/:searchString'
     *
     * @param dataSourceId
     * @param page
     * @param limit
     * @param sort
     * @param sortDirection
     * @param search
     * @param searchString
     * @return
     */
    @RequestMapping(value = "/{dataSourceId}/rows", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<DataSourceRow> showDataSourceData(@PathVariable("dataSourceId") String dataSourceId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "sort", required = false) Integer sort,
            @RequestParam(value = "sortDir", required = false) Integer sortDirection,
            @RequestParam(value = "search", required = false) Integer search,
            @RequestParam(value = "searchString", required = false) String searchString) {

        PageSpecBuilder specBulder = new PageSpecBuilderMongo("dataSourceFields");
        PageSpeciFication pageSpecification = specBulder.setPage(page, limit).setSort(sort, sortDirection).setSearch(search, searchString).getPageSpecification();

        List<DataSourceRow> findByDataSourceIdPaginated = dataSourceRowService.searchRows(dataSourceId, pageSpecification);
        return findByDataSourceIdPaginated;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveDatasourcer(@Valid @RequestBody DataSource dataSource, BindingResult result) {

        return saveDataSource(dataSource, result);

    }

    @RequestMapping(value = "/{dataSourceId}", consumes = MediaTypes.MEDIA_TYPEJSONUTF8VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity updateDataSource(@Valid @RequestBody DataSource dataSource, BindingResult result) {
        return saveDataSource(dataSource, result);

    }

    @RequestMapping(value = "/{dataSourceId}", method = RequestMethod.DELETE, produces = MediaTypes.MEDIA_TYPEJSONUTF8VALUE)
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
            dataSource.setDateUpdated(new Date());
            DataSource savedDataSource = dataRepository.save(dataSource);

            if (dataSource.getFileUploaded()) {
                Long i = dataSourceRowService.updateDataFromFile(savedDataSource.getId(), new File("/tmp/" + dataSource.getDataStructure().getFileName()));
                savedDataSource.setRecordsCount(i);
                dataRepository.save(savedDataSource);
            }

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

    /**
     * @return the dataSourceRowService
     */
    public DataSourceRowService getDataSourceRowService() {
        return dataSourceRowService;
    }

    /**
     * @param dataSourceRowService the dataSourceRowService to set
     */
    public void setDataSourceRowService(DataSourceRowService dataSourceRowService) {
        this.dataSourceRowService = dataSourceRowService;
    }

}
