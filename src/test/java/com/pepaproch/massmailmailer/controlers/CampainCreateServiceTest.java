/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Email;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;
/**
 *
 * @author pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
public class CampainCreateServiceTest {
    
    @Autowired
    private CampainCreateService campainCreateService;
    
    public CampainCreateServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of processCampain method, of class CampainCreateService.
     */
    @Test
    public void testProcessCampain() throws Exception {
    }

    /**
     * Test of createEmails method, of class CampainCreateService.
     */
    @Test
    public void testCreateEmails() throws Exception {
    }

    /**
     * Test of createEmail method, of class CampainCreateService.
     */
    @Test
    public void testCreateEmail() throws Exception {
    }

    /**
     * Test of proccesEmailBody method, of class CampainCreateService.
     */
    @Test
    public void testProccesEmailBody() {
    }

    /**
     * Test of proccesAttachment method, of class CampainCreateService.
     */
    @Test
    public void testProccesAttachment() {
    }

    /**
     * Test of getRowsrepository method, of class CampainCreateService.
     */
    @Test
    public void testGetRowsrepository() {
    }

    /**
     * Test of setRowsrepository method, of class CampainCreateService.
     */
    @Test
    public void testSetRowsrepository() {
    }

    /**
     * Test of getCampainService method, of class CampainCreateService.
     */
    @Test
    public void testGetCampainService() {
    }

    /**
     * Test of setCampainService method, of class CampainCreateService.
     */
    @Test
    public void testSetCampainService() {
    }

    /**
     * Test of getTemplateService method, of class CampainCreateService.
     */
    @Test
    public void testGetTemplateService() {
    }

    /**
     * Test of setTemplateService method, of class CampainCreateService.
     */
    @Test
    public void testSetTemplateService() {
    }

    /**
     * Test of getDataSourceRep method, of class CampainCreateService.
     */
    @Test
    public void testGetDataSourceRep() {
    }

    /**
     * Test of setDataSourceRep method, of class CampainCreateService.
     */
    @Test
    public void testSetDataSourceRep() {
    }

    /**
     * Test of getConvertService method, of class CampainCreateService.
     */
    @Test
    public void testGetConvertService() {
    }

    /**
     * Test of setConvertService method, of class CampainCreateService.
     */
    @Test
    public void testSetConvertService() {
    }

    /**
     * Test of getEmailrepo method, of class CampainCreateService.
     */
    @Test
    public void testGetEmailrepo() {
    }

    /**
     * Test of setEmailrepo method, of class CampainCreateService.
     */
    @Test
    public void testSetEmailrepo() {
    }

    /**
     * Test of getMailgunClient method, of class CampainCreateService.
     */
    @Test
    public void testGetMailgunClient() {
    }

    /**
     * Test of setMailgunClient method, of class CampainCreateService.
     */
    @Test
    public void testSetMailgunClient() {
    }

    /**
     * Test of getEmailFoldeRepo method, of class CampainCreateService.
     */
    @Test
    public void testGetEmailFoldeRepo() {
    }

    /**
     * Test of setEmailFoldeRepo method, of class CampainCreateService.
     */
    @Test
    public void testSetEmailFoldeRepo() {
    }

    /**
     * Test of geCreatePreview method, of class CampainCreateService.
     */
    @Test
    public void testGeCreatePreview() {
        Long campainId = 3L;
        for(int i = 1; i<4 ;i++) {
            Email geCreatePreview = campainCreateService.geCreatePreview(campainId, new Long(i));
            assertNotNull(geCreatePreview);
        }
        
        
    }

    /**
     * @return the campainCreateService
     */
    public CampainCreateService getCampainCreateService() {
        return campainCreateService;
    }

    /**
     * @param campainCreateService the campainCreateService to set
     */
    public void setCampainCreateService(CampainCreateService campainCreateService) {
        this.campainCreateService = campainCreateService;
    }
    
}
