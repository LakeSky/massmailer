/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import java.io.File;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class XLSXProcessorTest {

    public XLSXProcessorTest() {
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

    /**
     * Test of getRows method, of class CsvProcessor.
     */
    @Test
    public void testGetRows() {
        System.out.println("getRows");
        int count = 0;
        File f = new File("/home/pepa/test.xlsx");
        Collection<Collection<DataSourceField>> expResult = null;
        PoiFlatFileHandler processor = new XLSProcessor(new XSSRowToSrcRowMapper());
        RowMapper<RowRecords> rows = processor.process(f);
        int rowCout = 0;
        for (RowRecords r : rows) {
            rowCout++;

        }

        assertEquals(rowCout, 2);
    }

}
