/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataStructureMetaField;
import com.pepaproch.massmailmailer.db.documents.DataStructureMeta;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.poi.DataType;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author pepa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@WebAppConfiguration
public class DataSourceControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    
    private DataSource dataSource;
    private final List<String> cleanUp = new ArrayList<String>();

    @Autowired
    private DataSourceInfoRep dataSourceRep;
    private String dataSourceID;
    @Resource
    WebApplicationContext wac;

    private MockMvc mockMvc;

    public DataSourceControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

  @Before
    public void setUp() {
          mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
          dataSource = new DataSource();
        dataSource.setName("NAME_TEST" );
        List<DataStructureMetaField> fields = new ArrayList();
        for (int i = 0; i < 6; i++) {
          DataStructureMetaField field = new DataStructureMetaField(i, i + "display_name", DataType.TEXT);
           fields.add(field);
        }
        String toString = fields.toString();
        DataStructureMeta dataStructureMeta = new DataStructureMeta(fields);
        dataSource.setDataStructureMeta(dataStructureMeta);
        DataSource save = dataSourceRep.save(dataSource);
        dataSourceID = save.getId();
        
    }
    @After
    public void tearDown() {
        dataSourceRep.delete(dataSourceID);
    }

    /**
     * Test of listDatasourcer method, of class DataSourceController.
     * @throws java.lang.Exception
     */
    @Test
    public void testListDatasourcer() throws Exception {

        ResultActions perform = mockMvc.perform(get("/datasource").locale(Locale.ENGLISH).accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        MvcResult andReturn = perform.andReturn();
        MockHttpServletResponse response = andReturn.getResponse();
    }

    /**
     * Test of showDataSourcer method, of class DataSourceController.
     * @throws java.lang.Exception
     */
    @Test
    public void testShowDataSourcer() throws Exception {
        ResultActions perform = mockMvc.perform(get("/datasource/" + dataSourceID).locale(Locale.ENGLISH).accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
        MvcResult andReturn = perform.andReturn();
        MockHttpServletResponse response = andReturn.getResponse();
    }


}
