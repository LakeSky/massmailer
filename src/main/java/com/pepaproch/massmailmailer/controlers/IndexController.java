/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.mail.mailgun.MailGunRestClient;
import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.MailgunStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pepa
 */
@Controller(value = "IndexController")

@RequestMapping({"/"})
public class IndexController {
    @Autowired
    private MailGunRestClient client;
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        //  MailgunStatus events = client.getEvents();
    return "redirect:index.html";
    }

    /**
     * @return the client
     */
    public MailGunRestClient getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(MailGunRestClient client) {
        this.client = client;
    }
    
}
