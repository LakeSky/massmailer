/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private CampainStatusService campainStatusService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public CampainStatus getCampainStatus() {
        return getCampainStatusService().getAllCampainsStatus();
    }

    @RequestMapping(value = "/{campainId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public CampainStatus getCampainStatusByCampain(@PathVariable("campainId") Long campainId) {
        return getCampainStatusService().getCampainStatus(campainId);
    }

    /**
     *
     * @param campainIds
     * @param separated
     * @return
     */
    @RequestMapping(value = "/campains/combined", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public  List<CampainStatus> getCampainStatusByCampain(@RequestParam("campainIds") List<Long> campainIds, @RequestParam("separated") Boolean separated) {
        return getCampainStatusService().getCampainsStatus(campainIds, separated);
    }

    /**
     * @return the campainStatusService
     */
    public CampainStatusService getCampainStatusService() {
        return campainStatusService;
    }

    /**
     * @param campainStatusService the campainStatusService to set
     */
    public void setCampainStatusService(CampainStatusService campainStatusService) {
        this.campainStatusService = campainStatusService;
    }

}
