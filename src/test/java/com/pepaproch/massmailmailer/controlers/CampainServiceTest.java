/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Campain;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
public class CampainServiceTest {

    @Autowired
    private CampainService service;

    public CampainServiceTest() {
    }

    @Before
    public void setUp() throws IOException {
        File f = new File("/home/pepa/NetBeansProjects/MassMailMailer/src/main/resources/testtemplate.doc");
        File oF = new File("/tmp/testsave.doc");
        Files.copy(f.toPath(), oF.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public BigDecimal testSave() throws Exception {

        Campain c = new Campain();
        c.setId(new BigDecimal("333333"));
        c.setCampainName("CampainName");
        c.setAttachmentName("testsave.doc");
        return service.save(c).getId();

    }

    /**
     * Test of findOne method, of class CampainService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindOne() throws Exception {
        BigDecimal testSave = testSave();

        File oF = new File("/tmp/testsave.doc");
        if (oF.exists()) {
            oF.delete();
        }

        service.findOne(testSave);
        assertTrue(oF.exists());

        if (oF.exists()) {
            oF.delete();
        }
        assertTrue(!oF.exists());

    }

    /**
     * Test of getCampainRepo method, of class CampainService.
     */
    @Test
    public void testGetCampainRepo() {
    }

    /**
     * Test of setCampainRepo method, of class CampainService.
     */
    @Test
    public void testSetCampainRepo() {
    }

    /**
     * Test of getEntityManager method, of class CampainService.
     */
    @Test
    public void testGetEntityManager() {
    }

    /**
     * Test of setEntityManager method, of class CampainService.
     */
    @Test
    public void testSetEntityManager() {
    }

    /**
     * Test of findAll method, of class CampainService.
     */
    @Test
    public void testFindAll() {
    }

    /**
     * @return the service
     */
    public CampainService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(CampainService service) {
        this.service = service;
    }

}
