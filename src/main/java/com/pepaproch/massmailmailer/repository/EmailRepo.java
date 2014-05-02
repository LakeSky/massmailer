/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Email;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author pepa
 */
public interface EmailRepo extends CrudRepository<Email, Long>, PagingAndSortingRepository<Email, Long>,JpaRepository<Email, Long> {

    @Query("SELECT e FROM Email e WHERE campain.id = :campainId")
    public Collection<Email> findByCampainId(@Param("campainId") Long campainId);

    @Query("SELECT e FROM Email e WHERE e.emailStatus = :status")
    public Collection<Email> getByStatus(@Param("status") String status);

    @Query("SELECT e FROM Email e WHERE e.campain.id = :campainId and e.emailFolder.id = :folderId")
    public List<Email> findUnsentPaginated(@Param("campainId")  Long campainId,@Param("folderId") Long folderId, Pageable page);

    @Query(value = "SELECT count(e) FROM Email e WHERE e.campain.id = :campainId and e.emailFolder.id = :folderId")
    public long countUnsentPaginated(@Param("campainId") Long campainId,@Param("folderId") Integer folderId);
}
