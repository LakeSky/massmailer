/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.StatusHistory;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepa
 */

public interface StatusHistoryRepo extends CrudRepository<StatusHistory,Long> {
    
    @Query("SELECT MAX(s.lastDate) FROM StatusHistory s")
    public Date getLastStatusDate();
        

}
