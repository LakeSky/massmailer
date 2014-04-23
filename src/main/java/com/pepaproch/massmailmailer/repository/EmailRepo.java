/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Email;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author pepa
 */
public interface EmailRepo extends CrudRepository<Email, BigDecimal>, PagingAndSortingRepository<Email, BigDecimal> {

    @Query("SELECT e FROM Email e WHERE campain.id = ?0")
    public Collection<Email> findByCampainId(BigDecimal id);

    @Query("SELECT e FROM Email e WHERE e.emailStatus = :status")
    public Collection<Email> getByStatus(@Param("status") String status);

    @Query("SELECT e FROM Email e WHERE e.campain.id = ?0 and e.emailFolder.id = ?1")
    public List<Email> findUnsentPaginated(BigDecimal campainId, BigDecimal folderId, Pageable page);

    @Query(value = "SELECT count(e) FROM Email e WHERE e.campain.id = ?0 and e.emailFolder.id = ?1")
    public long countUnsentPaginated(BigDecimal campainId, BigDecimal folderId);
}
