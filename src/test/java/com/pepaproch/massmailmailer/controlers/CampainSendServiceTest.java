/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.CampainAttachment;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import java.util.HashSet;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class CampainSendServiceTest {

    @Autowired
    private CampainCreateService createService;
    @Autowired
    private CampainRepo campainRepo;


    public CampainSendServiceTest() {
    }

    /**
     * Test of processCampain method, of class CampainSendService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testProcessCampain() throws Exception {
        Campain c = new Campain();

        c.setCampainName("CampainName");
        HashSet<CampainAttachment> ats = new HashSet();
        CampainAttachment at = new CampainAttachment();
        at.setAttachmentName("testsave.doc");
        at.setAttachmentFileType("doc");
        at.setAttachmentFileSystemName("test.doc");
        at.setAttachmentFileType("pdf");
        at.setAttachmentOutputName("output.pdf");
        at.setAttachmentOutputType("pdf");
        at.setCustomizeAttachments(Boolean.TRUE);
        at.setCampain(c);
        ats.add(at);
        CampainAttachment ata = new CampainAttachment();
        ata.setAttachmentName("testsave.odt");
        ata.setAttachmentFileType("odt");
        ata.setAttachmentFileSystemName("test.odt");
        ata.setAttachmentFileType("odt");
        ata.setAttachmentOutputName("output.odt");
        ata.setAttachmentOutputType("odt");
        ata.setCustomizeAttachments(Boolean.FALSE);
        ata.setCampain(c);
        ats.add(ata);

        c.setRecipients("EMAIL");
        c.setCampainAttachments(ats);
        c.setEmailText("<p>nasleduje test STRING: ###STRING### </p>");
        c.setSubject("###STRING### : STRING");
        c.setDataSourceId("52fcd88844aef19a6f3c74db");
       Campain cs =  campainRepo.save(c);
        createService.processCampain(cs.getId());


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
    public CampainCreateService getSendService() {
        return createService;
    }

    /**
     * @param sendService the sendService to set
     */
    public void setSendService(CampainCreateService sendService) {
        this.createService = sendService;
    }



}
