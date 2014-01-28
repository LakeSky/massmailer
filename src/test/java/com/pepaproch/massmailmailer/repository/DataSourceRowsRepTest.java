/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.db.documents.DataSourceInfo;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataSourceRowField;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import java.util.ArrayList;
import java.util.Collection;
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
public class DataSourceRowsRepTest {

    private final String dataSourceId = "52e268ee44ae7b387a09711c";
    private final List<String> cleanUp = new ArrayList<String>();
    @Autowired
    private DataSourceRowsRep dataSourceRowRep;

    public DataSourceRowsRepTest() {
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
        for(String d : cleanUp) {
        dataSourceRowRep.delete(d);
        }
    }

    @Test
    public void findAll() {
        // TODO review the generated test code and remove the default call to fail.
        Iterable<DataSourceRow> findAll = dataSourceRowRep.findAll();
        assertNotNull(findAll);
    }

    @Test
    public void insertAll() {

        //find datasourceby id;
        List<DataSourceRow> rows = new ArrayList();
        for (int i = 0; i < 10; i++) {

            List<DataSourceRowField> fields = new ArrayList();
            for (int r = 0; r < 6; r++) {
                DataSourceRowField F = new DataSourceRowField(r, r + "_value" + i);
                fields.add(F);

            }
            DataSourceRow row = new DataSourceRow(dataSourceId, fields);
            rows.add(row);

        }
        Iterable<DataSourceRow> save = dataSourceRowRep.save(rows);
        Collection<DataSourceRow> findByColumnValue = dataSourceRowRep.findByColumnValue(dataSourceId, 2, "2_value3");
        assertNotNull(findByColumnValue);
        assertTrue("FIND ONE",findByColumnValue.size()==1);
        
        for(DataSourceRow d : save) {
        assertNotNull(d.getId());
        cleanUp.add(d.getId());
        }
        assertNotNull(save);
       

    }

}
