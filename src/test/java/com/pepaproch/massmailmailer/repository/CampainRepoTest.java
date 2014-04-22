/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.Users;
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
public class CampainRepoTest {
    
    @Autowired
    private CampainRepo reporistory ;
    
    public CampainRepoTest() {
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

    @Test
    public void findAll() {
        Iterable<Campain> findAll = getReporistory().findAll();
        assertNotNull(findAll);

    }
    


    @Test
    public void insert() {


    }

    /**
     * @return the reporistory
     */
    public CampainRepo getReporistory() {
        return reporistory;
    }

    /**
     * @param reporistory the reporistory to set
     */
    public void setReporistory(CampainRepo reporistory) {
        this.reporistory = reporistory;
    }
    

    
}
