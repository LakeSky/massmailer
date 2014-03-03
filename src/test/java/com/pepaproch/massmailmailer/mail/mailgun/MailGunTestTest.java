/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mail.mailgun;

import com.sun.jersey.api.client.ClientResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class MailGunTestTest {
    
    public MailGunTestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of SendSimpleMessage method, of class MailGunTest.
     */
    @Test
    public void testSendSimpleMessage() {
        System.out.println("SendSimpleMessage");
        ClientResponse expResult = null;
        ClientResponse result = MailGunTest.SendSimpleMessage();
        
      SentEmailResponse res = result.getEntity(SentEmailResponse.class);
        System.out.println(result.toString());
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
  
    }
    
    
        /**
     * Test of SendSimpleMessage method, of class MailGunTest.
     */
    @Test
    public void getStatus() {
        System.out.println("SendSimpleMessage");
        ClientResponse expResult = null;
       String result = MailGunTest.getStatus();
        

        System.out.println(result.toString());
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
  
    }
    
}
