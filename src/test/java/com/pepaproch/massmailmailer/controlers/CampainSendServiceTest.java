/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import java.util.Collection;
import javax.transaction.Transactional;
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
public class CampainSendServiceTest {
    
    @Autowired
    private CampainSendService sendService;
    @Autowired
    private CampainRepo  campainRepo ;
    @Autowired
    private EmailRepo  emailrepo;
    
    public CampainSendServiceTest() {
    }

    /**
     * Test of processCampain method, of class CampainSendService.
     * @throws java.lang.Exception
     */
    @Test
    public void testProcessCampain() throws Exception {
         Campain c = new Campain();

        c.setCampainName("CampainName");
        c.setAttachmentName("testsave.doc");
        c.setAttachmentFileType("doc");
        c.setAttachmentFileSystemName("test.doc");
        c.setAttachmentFileType("pdf");
        c.setAttachmentOutputName("output.pdf");
        c.setAttachmentOutputType("pdf");
        c.setRecipients("####EMAIL####");
        c.setCustomizeAttachments(Boolean.TRUE);
        c.setEmailText("<p>nasleduje test STRING: ####STRING#### </p>");
        c.setSubject("####STRING#### : STRING");
        c.setDataSourceId("52fcd88844aef19a6f3c74db");
        Campain save = campainRepo.save(c);
        sendService.processCampain(c);
        Collection<Email> findByCampainId = emailrepo.findByCampainId(save.getId());
        assertTrue(findByCampainId.size()>0);
       
    }

    /**
     * Test of proccesEmailBody method, of class CampainSendService.
     */
    @Test
    public void testProccesEmailBody() {
    }

    /**
     * Test of proccesAttachment method, of class CampainSendService.
     */
    @Test
    public void testProccesAttachment() {
    }

    /**
     * Test of getRowsrepository method, of class CampainSendService.
     */
    @Test
    public void testGetRowsrepository() {
    }

    /**
     * Test of setRowsrepository method, of class CampainSendService.
     */
    @Test
    public void testSetRowsrepository() {
    }

    /**
     * Test of getCampainService method, of class CampainSendService.
     */
    @Test
    public void testGetCampainService() {
    }

    /**
     * Test of setCampainService method, of class CampainSendService.
     */
    @Test
    public void testSetCampainService() {
    }

    /**
     * @return the campainRepo
     */
    public CampainRepo getCampainRepo() {
        return campainRepo;
    }

    /**
     * @param campainRepo the campainRepo to set
     */
    public void setCampainRepo(CampainRepo campainRepo) {
        this.campainRepo = campainRepo;
    }

    /**
     * @return the sendService
     */
    public CampainSendService getSendService() {
        return sendService;
    }

    /**
     * @param sendService the sendService to set
     */
    public void setSendService(CampainSendService sendService) {
        this.sendService = sendService;
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
    
}
