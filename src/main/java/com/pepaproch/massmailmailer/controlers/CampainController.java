/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import java.math.BigDecimal;
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
    private CampainRepo campainRepo;

    /**
     *
     * @return
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody

    public List<Campain> listCampain() {
        return (List) campainRepo.findAll();
    }

    @RequestMapping(value = "/{campainId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Campain getCampain(@PathVariable("campainId") BigDecimal campainId) {

        return campainRepo.findOne(campainId);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveCampain(@Valid @RequestBody Campain campain, BindingResult result) {
        campainRepo.save(campain);
        throw new UnsupportedOperationException();
    }

    @RequestMapping(value = "/{dataSourceId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity updateCampain(@Valid @RequestBody Campain campain, BindingResult result) {
        campainRepo.save(campain);
        throw new UnsupportedOperationException();
    }

    @RequestMapping(value = "/{dataSourceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteDataSource(@PathVariable("campainId") BigDecimal campainId) {
        campainRepo.delete(campainId);
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

}
