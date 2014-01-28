/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.db.documents.DataSourceInfo;
import com.pepaproch.massmailmailer.db.documents.DataStructureField;
import com.pepaproch.massmailmailer.db.documents.DataStructureInfo;
import java.util.ArrayList;
import java.util.List;
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

    private DataSourceInfo dataSource;
    private final List<String> cleanUp = new ArrayList<String>();

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
        dataSource = new DataSourceInfo();
        dataSource.setName("NAME_TEST" );
        List<DataStructureField> fields = new ArrayList();
        for (int i = 0; i < 6; i++) {
          DataStructureField field = new DataStructureField(i, i + "display_name");
           fields.add(field);
        }
        String toString = fields.toString();
        DataStructureInfo dataStructureInfo = new DataStructureInfo(fields);
        dataSource.setDataStructureInfo(dataStructureInfo);
    }

    @After
    public void tearDown() {
        for(String id : cleanUp) {
        dataSourceRep.delete(id);
        }
    }

    @Test
    public void findAll() {
        // TODO review the generated test code and remove the default call to fail.
        Iterable<DataSourceInfo> findAll = dataSourceRep.findAll();

        assertNotNull(findAll);
    }

    @Test
    public void insert() {

        DataSourceInfo save = dataSourceRep.save(dataSource);
        assertNotNull(save.getId());
        cleanUp.add(save.getId());

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
