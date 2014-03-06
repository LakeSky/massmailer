/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Email;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepa
 */

public interface EmailRepo extends CrudRepository<Email,BigDecimal>{
     @Query("SELECT e FROM Email e WHERE campain.id = ?0")
    public Collection<Email> findByCampainId(BigDecimal id);
}
