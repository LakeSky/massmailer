/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.Email;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})

public class EmailRepoTest {

    @Autowired
    private EmailRepo emailRepo;

    @Autowired
    private CampainRepo campainRepo;

    public EmailRepoTest() {
    }

    /**
     * Test of findByCampainId method, of class EmailRepo.
     */
    @Test
    public void testFindByCampainId() {
    }

    @Test
    public void insertAndFidByCId() {
        Email e = emailRepo.save(createRecord());
        Collection<Email> findByCampainId = emailRepo.findByCampainId(e.getCampain().getId());
        assertTrue(findByCampainId.size() == 1);

    }

    @Test
    public void testFindUnsent() {
        List<Email> findUnsentPaginated = emailRepo.findUnsentPaginated(Long.valueOf(1),Long.valueOf(1), new PageRequest(0, 10));
        assertNotNull(findUnsentPaginated);
    }
    
        @Test
    public void testFindAll() {
        Collection<Email> findALl= (Collection<Email>) emailRepo.findAll();
        assertNotNull(findALl);
    }

    @Test
    public void testCountUnsent() {
        Long count = emailRepo.countUnsentPaginated(Long.valueOf(0),0L);
        assertTrue(count==0);
    }

    private Email createRecord() {
        Campain c = new Campain();
        c.setEmailText("test");
        c.setCampainName("dders");

        Email e = new Email();
        e.setFromEmail("ss@kkl.cz");
        e.setCampain(campainRepo.save(c));
        return e;

    }

    /**
     * @return the emailRepo
     */
    public EmailRepo getEmailRepo() {
        return emailRepo;
    }

    /**
     * @param emailRepo the emailRepo to set
     */
    public void setEmailRepo(EmailRepo emailRepo) {
        this.emailRepo = emailRepo;
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

}
