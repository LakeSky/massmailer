/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
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
@RequestMapping("/campain")
public class CampainController {

  @Autowired
  private CampainService campainService;
    @Autowired
    private CampainValidator campainValidator;

    /**
     *
     * @return
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody

    public List<Campain> listCampain() {
        
        return getCampainService().findAll();
    }

    @RequestMapping(value = "/{campainId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Campain getCampain(@PathVariable("campainId") BigDecimal campainId) {

        return getCampainService().findOne(campainId);
    }



    @RequestMapping(value = "/{campainId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity updateCampain(@Valid @RequestBody Campain campain, BindingResult result) throws FileNotFoundException {

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            ResponseEntity<List<FieldError>> errorResponse = new ResponseEntity<List<FieldError>>(fieldErrors, HttpStatus.UNPROCESSABLE_ENTITY);
            return errorResponse;
        } else {
         
             
              
            Campain campainSaved = getCampainService().save(campain);

            ResponseEntity<DataSource> responseEntity = new ResponseEntity(campainSaved, HttpStatus.CREATED);
            return responseEntity;
        }

    }

    @RequestMapping(value = "/{dataSourceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteDataSource(@PathVariable("campainId") BigDecimal campainId) {
        getCampainService().delete(campainId);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    public CampainController() {
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
    @RequestMapping(value = "/{dataSourceId}/rows/{page}/{limit}/{sort}/{sortDir}/{search}/{searchString}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<DataSourceRow> showDataSourceData(@PathVariable("dataSourceId") String dataSourceId,
            @PathVariable("page") String page,
            @PathVariable("limit") String limit,
            @PathVariable("sort") String sort,
            @PathVariable("sortDir") String sortDirection,
            @PathVariable("search") String search,
            @PathVariable("searchString") String searchString) {
        Sort sortable = new Sort(Sort.Direction.DESC, "dataSourceFields." + sort + ".value");
        Pageable pageSpecification = new PageRequest(new Integer(page), new Integer(limit), sortable);
//        List<DataSourceRow> findByDataSourceIdPaginated = dataSourceRowsRep.findByDataSourceIdPaginated(dataSourceId,new Integer(search),searchString, pageSpecification);
//        return findByDataSourceIdPaginated;\
        return null;
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
}
