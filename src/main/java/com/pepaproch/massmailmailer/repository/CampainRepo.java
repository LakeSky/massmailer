/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Campain;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pepa
 */

public interface CampainRepo extends CrudRepository<Campain,Long>,  PagingAndSortingRepository<Campain, Long>,JpaRepository<Campain, Long> ,JpaSpecificationExecutor<Campain>{
    
    
    @Query("Select c from Campain c where c.status = :status")
    public Collection<Campain> findByStatus(@Param("status") String status);
    
    

    
}
