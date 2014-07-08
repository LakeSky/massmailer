/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import static org.junit.Assert.assertNotNull;
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
public class EmailStatusRepoTest {

    @Autowired
    private EmailStatusRepo emailStatusRepo;

    public void setUp() {

    }

    @Test
    public void getSentForCampainTest() {
        Long sentOverAll = getEmailStatusRepo().getSentOverAll();
        assertNotNull(sentOverAll);

    }

    /**
     * @return the emailStatusRepo
     */
    public EmailStatusRepo getEmailStatusRepo() {
        return emailStatusRepo;
    }

    /**
     * @param emailStatusRepo the emailStatusRepo to set
     */
    public void setEmailStatusRepo(EmailStatusRepo emailStatusRepo) {
        this.emailStatusRepo = emailStatusRepo;
    }

}
