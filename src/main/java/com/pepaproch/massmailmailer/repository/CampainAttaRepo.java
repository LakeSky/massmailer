/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.CampainAttachment;
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

public interface CampainAttaRepo extends CrudRepository<CampainAttachment,Long>,  PagingAndSortingRepository<CampainAttachment, Long>,JpaRepository<CampainAttachment, Long> ,JpaSpecificationExecutor<CampainAttachment>{
    
    

    @Query("Select a from CampainAttachment a where a.campain.id = :campainId")
    public Collection<CampainAttachment> findByCampainId(@Param("campainId") Long  campainId);
    
    

    
}
