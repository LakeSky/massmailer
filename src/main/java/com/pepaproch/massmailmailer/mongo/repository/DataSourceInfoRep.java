/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mongo.repository;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepa
 */
public interface DataSourceInfoRep extends CrudRepository<DataSource, String> {

}
