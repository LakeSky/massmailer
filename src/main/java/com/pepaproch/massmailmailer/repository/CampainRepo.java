/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Campain;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepa
 */

public interface CampainRepo extends CrudRepository<Campain,Long>{
    
    
    @Query("Select c from Campain c where c.status = ?0")
    public Collection<Campain> findByStatus(String status);
    
}
