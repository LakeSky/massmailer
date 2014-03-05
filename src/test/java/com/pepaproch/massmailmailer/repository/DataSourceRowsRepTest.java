/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.poi.DataType;
import com.pepaproch.utils.DateUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        for (String d : cleanUp) {
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
    public void count() {
BigDecimal count = dataSourceRowRep.countByDataSourceId("52fcd88844aef19a6f3c74db");
            assertNotNull(count);
    }
    
    
    @Test
    public void deleteAll() {
        // TODO review the generated test code and remove the default call to fail.
        List<DataSourceRow> rows = new ArrayList();
        Collection<DataSourceRow> save = null;

        for (int i = 0; i < 10; i++) {
            List<DataSourceField> fields = new ArrayList();
            for (int r = 0; r < 6; r++) {
                DataSourceField<String> F = new DataSourceField(r, r + "_value" + i, DataType.TEXT);
                fields.add(F);
            }
            DataSourceField<Date> F = new DataSourceField(6, DateUtils.addDays(DateUtils.getStartDate(new Date()), i), DataType.DATE);
            fields.add(F);
            DataSourceRow row = new DataSourceRow(dataSourceId, fields);
            rows.add(row);

        }
        save = (Collection<DataSourceRow>) dataSourceRowRep.save(rows);

        assertEquals(save.size(), rows.size());

        dataSourceRowRep.delete(dataSourceRowRep.findByDataSourceId(dataSourceId));
        Collection<DataSourceRow> findByDataSourceId = dataSourceRowRep.findByDataSourceId(dataSourceId);
        assertEquals(findByDataSourceId.size(), 0);

    }

    @Test
    public void insertAll() {

        //find datasourceby id;
        List<DataSourceRow> rows = new ArrayList();
        Iterable<DataSourceRow> save = null;
        try {
            for (int i = 0; i < 10; i++) {
                List<DataSourceField> fields = new ArrayList();
                for (int r = 0; r < 6; r++) {
                    DataSourceField<String> F = new DataSourceField(r, r + "_value" + i, DataType.TEXT);
                    fields.add(F);
                }
                DataSourceField<Date> F = new DataSourceField(6, DateUtils.addDays(DateUtils.getStartDate(new Date()), i), DataType.DATE);
                fields.add(F);
                DataSourceRow row = new DataSourceRow(dataSourceId, fields);
                rows.add(row);

            }
            save = dataSourceRowRep.save(rows);
            assertNotNull(save);
            Collection<DataSourceRow> findByColumnValue = dataSourceRowRep.findByColumnValue(dataSourceId, 2, "2_value3");
            assertNotNull(findByColumnValue);
            assertTrue("FIND ONE", findByColumnValue.size() == 1);

            Collection<DataSourceRow> findByColumnDateValue = dataSourceRowRep.findByColumnValue(dataSourceId, 6, DateUtils.addDays(DateUtils.getStartDate(new Date()), 0));
            assertNotNull(findByColumnDateValue);
            assertTrue("FIND ONE", findByColumnDateValue.size() == 1);

            Sort sort = new Sort(Sort.Direction.ASC, "id");
            Pageable pageSpecification = new PageRequest(0, 2, sort);
//            Iterable<DataSourceRow> findAll = dataSourceRowRep.findByDataSourceIdPaginated("52fcd88844aef19a6f3c74db",0, pageSpecification);
//            assertNotNull(findAll);

        } finally {
            if (save != null) {
                for (DataSourceRow d : save) {
                    assertNotNull(d.getId());
                    cleanUp.add(d.getId());
                }
            }
        }

    }

}
