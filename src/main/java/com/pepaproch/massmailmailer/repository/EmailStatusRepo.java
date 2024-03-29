/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.EmailStatus;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pepa
 */
public interface EmailStatusRepo extends CrudRepository<EmailStatus, Long> {

    @Query("SELECT count(DISTINCT  s.email.id) FROM EmailStatus s where s.email.campain.id = :campainId")
    public Long getSentForCampain(@Param("campainId") Long campainId);

    @Query("SELECT count(DISTINCT  s.email.id) FROM EmailStatus s")
    public Long getSentOverAll();

    @Query("SELECT count(DISTINCT  s.email.campain.id) FROM EmailStatus s")
    public Long getSentCampainsOverAll();

    @Query("SELECT count(DISTINCT  s.email.id) FROM EmailStatus s where s.status= :status")
    public Long getByStatus(@Param("status") String status);

    @Query("SELECT count(DISTINCT  s.email.recipients) FROM EmailStatus s where s.status= :status")
    public Long getDistinctRecipientsForStatus(@Param("status") String status);

    @Query("SELECT count(DISTINCT  s.email.id) FROM EmailStatus s where s.email.campain.id = :campainId and s.status= :status")
    public Long getByStatusAndCampainId(@Param("status") String status, @Param("campainId") Long campainId);
    
     @Query("SELECT count(DISTINCT  s.email.id) FROM EmailStatus s where s.email.campain.id in (:campainIds) and s.status= :status")
    public Long getByStatusAndCampainIds(@Param("status") String status, @Param("campainIds") List<Long> campainIds);

}
