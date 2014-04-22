/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.mail.mailgun.MailGunRestClient;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class CampainSendService {


    private EmailRepo emailrepo;
    @Autowired
    private MailGunRestClient mailgunClient;


    
    private void  findNotsentCampains() {
    emailrepo.findByStaus("SENDING");
    }
    
    private void updateCampainStatus(){
    
    }

    /**
     * @return the emailrepo
     */
    public EmailRepo getEmailrepo() {
        return emailrepo;
    }

    /**
     * @param emailrepo the emailrepo to set
     */
    public void setEmailrepo(EmailRepo emailrepo) {
        this.emailrepo = emailrepo;
    }

    /**
     * @return the mailgunClient
     */
    public MailGunRestClient getMailgunClient() {
        return mailgunClient;
    }

    /**
     * @param mailgunClient the mailgunClient to set
     */
    public void setMailgunClient(MailGunRestClient mailgunClient) {
        this.mailgunClient = mailgunClient;
    }
}
