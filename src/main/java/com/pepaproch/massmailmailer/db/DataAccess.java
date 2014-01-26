/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db;

import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author pepa
 */
public class DataAccess {
    @Autowired
    private DataSourceInfoRep dataSourceInfoRep;
    @Autowired
    private UserRepo userRepo;

    /**
     * @return the dataSource
     */
    public DataSourceInfoRep getDataSourceInfoRep() {
        return dataSourceInfoRep;
    }

    /**
     * @param dataSourceInfoRep
     */
    
    @Autowired
    public void setDataSource(DataSourceInfoRep dataSourceInfoRep) {
        this.dataSourceInfoRep = dataSourceInfoRep;
    }

    /**
     * @return the userRepo
     */
    public UserRepo getUserRepo() {
        return userRepo;
    }

    /**
     * @param userRepo the userRepo to set
     */
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
}
