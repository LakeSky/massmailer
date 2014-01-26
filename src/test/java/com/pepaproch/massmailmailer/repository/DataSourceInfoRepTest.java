/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;


import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.db.documents.DataSourceInfo;
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
public class DataSourceInfoRepTest {
    @Autowired
    private DataSourceInfoRep dataSourceRep;
    
    public DataSourceInfoRepTest() {
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
        // TODO review the generated test code and remove the default call to fail.
        Iterable<DataSourceInfo> findAll = dataSourceRep.findAll();
     
        assertNotNull(findAll);
    }
    @Test
    public void insert() {
    DataSourceInfo dasource = new DataSourceInfo();

    dasource.setName("NMAE");
    
    DataSourceInfo save = dataSourceRep.save(dasource);
    assertNotNull(save.getId());
    }

    /**
     * @return the dataSourceRep
     */
    public DataSourceInfoRep getDataSourceRep() {
        return dataSourceRep;
    }

    /**
     * @param dataSourceRep the dataSourceRep to set
     */
    public void setDataSourceRep(DataSourceInfoRep dataSourceRep) {
        this.dataSourceRep = dataSourceRep;
    }
    
}
