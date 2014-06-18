/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.entity.Email;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private CampainService campainService;


    @Autowired
    private CampainCreateService campainSendService;

    /**
     *
     * @return
     */
    @RequestMapping(value="/emails/{campainId}",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Email> listEmails() {
        return null;
    }

    @RequestMapping(value = "/{campainId}/{emailidx}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Email getEmailPreview(@PathVariable("campainId") Long campainId, Long emailIdx ) {
    Email email =  campainSendService.geCreatePreview(campainId,emailIdx);
    return email;
    }



    public EmailController() {
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
    //    binder.setValidator(getCampainValidator());

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
