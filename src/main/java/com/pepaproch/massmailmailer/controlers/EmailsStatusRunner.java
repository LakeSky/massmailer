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
public class EmailsStatusRunner {
    
    @Autowired
    private EmailsStatusService emailStatusService;

    @Scheduled(fixedDelay = 100000)
    private void getStatuses() {
        getEmailStatusService().updateStatuses();
        
    }

    /**
     * @return the emailStatusService
     */
    public EmailsStatusService getEmailStatusService() {
        return emailStatusService;
    }

    /**
     * @param emailStatusService the emailStatusService to set
     */
    public void setEmailStatusService(EmailsStatusService emailStatusService) {
        this.emailStatusService = emailStatusService;
    }


}
