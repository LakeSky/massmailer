/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataStructure;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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

public class TemlateServiceTest {
    
    @Autowired
    private TemlateService service;
    
    public TemlateServiceTest() {
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
     * Test of createPreview method, of class TemlateService.
     */
    @Test
    public void testCreatePreview() throws Exception {
        System.out.println("createPreview");
        String templateFile = "";
        String dataSourceId = "";
        String name =  getService().createPreview("/home/pepa/NetBeansProjects/massmailer/massmailer/src/main/resources/testtemplate.doc", "530f09f644ae3bbdfd1e34fb");
  
        // TODO review the generated test code and remove the default call to fail.
        assertNotNull(name);
    }







    /**
     * @return the service
     */
    public TemlateService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(TemlateService service) {
        this.service = service;
    }
    
}
