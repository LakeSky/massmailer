/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db;

import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.repository.UserRepo;
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
public class DataAccessTest {
    
    @Autowired
    private DataAccess instance;

    public DataAccessTest() {
    }


    /**
     * @return the instance
     */
    public DataAccess getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(DataAccess instance) {
        this.instance = instance;
    }

    /**
     * Test of getDataSourceInfoRep method, of class DataAccess.
     */
    @Test
    public void testGetDataSourceInfoRep() {
           System.out.println("getDataSourceInfoRep");
        DataSourceInfoRep dataSourceInfoRep = instance.getDataSourceInfoRep();
        assertNotNull(dataSourceInfoRep);
    }

    /**
     * Test of getUserRepo method, of class DataAccess.
     */
    @Test
    public void testGetUserRepo() {
        System.out.println("getUserRepo");
        UserRepo userRepo = instance.getUserRepo();
        assertNotNull(userRepo);

    }

   

}
