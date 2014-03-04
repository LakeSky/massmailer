/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.MailgunStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
/**
 *
 * @author pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class MailGunRestClientTest {
    
    @Autowired
    private MailGunRestClient client;
    
    public MailGunRestClientTest() {
    }

    /**
     * Test of sendEmail method, of class MailGunRestClient.
     */
    @Test
    public void testSendEmail() {
        client.sendEmail();
    }
    
        @Test
    public void getStatus() {
        MailgunStatus events = client.getEvents();
            assertNotNull(events);
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
