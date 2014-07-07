/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.Item;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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



    @Test
    public void getStatus() {

        List<Item> events = null;
        try {
            events = client.getEvents(new SimpleDateFormat("yyyy.MM.dd hh:mm").parse("2014.07.05 13:00"),new Date());
        } catch (ParseException ex) {
            Logger.getLogger(MailGunRestClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(events);
        for(Item i: events) {
            System.out.println(i.getTimestampAsDate());
            
         //   System.out.println((new Date(i.getTimestamp())) + " " +  i.getMessage().getHeaders().getMessage_id() + " " + i.getEvent());
        }
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
