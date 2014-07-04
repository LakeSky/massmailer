/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.EmailFolder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pepa
 */

public interface EmailFolderRepo extends CrudRepository<EmailFolder,Long> {
    
    @Query("SELECT f FROM EmailFolder f WHERE f.id = :folderId")
    public EmailFolder getByEmailFolderId(@Param("folderId") Long folderId);
    

    

}
