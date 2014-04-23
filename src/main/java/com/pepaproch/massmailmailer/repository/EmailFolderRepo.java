/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.db.entity.EmailFolder;
import java.math.BigDecimal;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepa
 */

public interface EmailFolderRepo extends CrudRepository<Email,BigDecimal> {
    
     @Query("SELECT f FROM EmailFolder f WHERE f.id = ?0")
    public EmailFolder findByEmailFolderId(BigDecimal id);
    

    

}
