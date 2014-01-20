/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.service;

import com.pepaproch.massmailmailer.db.entity.DataSource;
import com.pepaproch.massmailmailer.db.entity.Users;
import com.pepaproch.massmailmailer.repository.DataSourceRep;
import com.pepaproch.massmailmailer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class DataSourceService {
   @Autowired
   private DataSourceRep dataSourceRep; 


    
    public Iterable<DataSource> listDataSources() {
    return getDataSourceRep().findAll();
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
