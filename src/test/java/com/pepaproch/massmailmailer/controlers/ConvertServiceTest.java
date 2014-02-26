/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

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

public class ConvertServiceTest {
    @Autowired
    private ConvertService service;
    
    public ConvertServiceTest() {
    }

    /**
     * Test of convert method, of class ConvertService.
     */
    @Test
    public void testConvert() {
        service.convert("/tmp/52fcd88844aef19a6f3c74db530320e444ae50d084842e2ftest.doc", "/tmp/52fcd88844aef19a6f3c74db530320e444ae50d084842e2ftest.pdf", Boolean.FALSE);
    }

    /**
     * @return the service
     */
    public ConvertService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ConvertService service) {
        this.service = service;
    }
    
}
