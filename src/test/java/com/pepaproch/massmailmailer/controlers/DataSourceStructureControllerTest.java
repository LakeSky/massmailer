/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import java.nio.charset.Charset;
import java.util.Locale;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class DataSourceStructureControllerTest {
        public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
        @Resource
    WebApplicationContext wac;

    private MockMvc mockMvc;
    
    public DataSourceStructureControllerTest() {
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
    }
    @After
    public void tearDown() {
    }

    /**
     * Test of getStructure method, of class DataSourceStructureController.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetStructure() throws Exception {
        ResultActions perform = mockMvc.perform(get("/datasource/structure/1").locale(Locale.ENGLISH).accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        MvcResult andReturn = perform.andReturn();
        MockHttpServletResponse response = andReturn.getResponse();
    }
    
}
