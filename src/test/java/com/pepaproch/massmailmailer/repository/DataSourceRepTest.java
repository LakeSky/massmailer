/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.DataSource;
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
public class DataSourceRepTest {
    @Autowired
    private DataSourceRep dataSourceRep;
    
    public DataSourceRepTest() {
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
        Iterable<DataSource> findAll = dataSourceRep.findAll();
        findAll.;
        assertNotNull(findAll);
    }
    @Test
    public void insert() {
    DataSource dasource = new DataSource();
    dasource.setFileName("test");
    dasource.setName("NMAE");
    dasource.setRecordsCount(2);
    DataSource save = dataSourceRep.save(dasource);
    assertNotNull(save.getId());
    }

    /**
     * @return the dataSourceRep
     */
    public DataSourceRep getDataSourceRep() {
        return dataSourceRep;
    }

    /**
     * @param dataSourceRep the dataSourceRep to set
     */
    public void setDataSourceRep(DataSourceRep dataSourceRep) {
        this.dataSourceRep = dataSourceRep;
    }
    
}
