/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class CampainSendRunner {
    
    @Autowired
    private CampainSendService sendService;

    @Scheduled(fixedDelay = 100000)
    private void sendCampains() {
        getSendService().send();
    }

    /**
     * @return the sendService
     */
    public CampainSendService getSendService() {
        return sendService;
    }

    /**
     * @param sendService the sendService to set
     */
    public void setSendService(CampainSendService sendService) {
        this.sendService = sendService;
    }
}
