/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;

import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
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
 * @autho
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
r pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})

public class TemlateServiceTest {
    @Autowired
    private  Object  dataSourceRowsRep;
    @Autowired
    private DataSourceInfoRep dataSourceInfoRep;
    @Autowired
    private TemlateService service;
    
    public TemlateServiceTest() {
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
     * Test of createPreview method, of class TemlateService.
     */
    @Test
    public void testCreatePreview() throws Exception {
        System.out.println("createPreview");
        String templateFile = "";
        String dataSourceId = "";
        String name =  getService().createPreview("/home/pepa/NetBeansProjects/MassMailMailer/src/main/resources/testtemplate.doc", "52fcd88844aef19a6f3c74db");
  
        // TODO review the generated test code and remove the default call to fail.
        assertNotNull(name);
    }


    @Test
    public void testPopulateWord() throws Exception {
        DataSource ds = getDataSourceInfoRep().findOne("52fcd88844aef19a6f3c74db");
//        Sort sortable = new Sort(Sort.Direction.ASC, "dataSourceFields." + 0 + ".value");
//        Pageable pageSpecification = new PageRequest(1, 1, sortable);
//        Collection<DataSourceRow> findByDataSourceIdPage = (List<DataSourceRow>) getDataSourceRowsRep().findByDataSourceIdPage("52fcd88844aef19a6f3c74db", pageSpecification);
//        if (findByDataSourceIdPage == null || findByDataSourceIdPage.size() == 0) {
//            throw new IllegalArgumentException("DataSource not found: " + "52fcd88844aef19a6f3c74db");
//        }
//        DocumentHolder docu = new WordDocument("/home/pepa/NetBeansProjects/MassMailMailer/src/main/resources/testtemplate.doc", new StringPlaceHolderHelper("####"));
//        String result =  service.populateTemplate(docu, ds.getDataStructure(), findByDataSourceIdPage.iterator().next());
//        File f = new File("/tmp/"+ result);
//        assertTrue(f.exists());
    }






    /**
     * @return the service
     */
    public TemlateService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(TemlateService service) {
        this.service = service;
    }

    /**
     * @return the dataSourceInfoRep
     */
    public DataSourceInfoRep getDataSourceInfoRep() {
        return dataSourceInfoRep;
    }

    /**
     * @param dataSourceInfoRep the dataSourceInfoRep to set
     */
    public void setDataSourceInfoRep(DataSourceInfoRep dataSourceInfoRep) {
        this.dataSourceInfoRep = dataSourceInfoRep;
    }

//    /**
//     * @return the dataSourceRowsRep
//     */
//    public DataSourceInfoRep getDataSourceRowsRep() {
//        return dataSourceRowsRep;
//    }
//
//    /**
//     * @param dataSourceRowsRep the dataSourceRowsRep to set
//     */
//    public void setDataSourceRowsRep(DataSourceRowsRep dataSourceRowsRep) {
//        this.dataSourceRowsRep = dataSourceRowsRep;
//    }
    
}
