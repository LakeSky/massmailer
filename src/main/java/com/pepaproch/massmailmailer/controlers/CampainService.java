/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class CampainService {

    @Autowired
    private CampainRepo campainRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public Campain save(Campain c) throws FileNotFoundException {
        Session session = getEntityManager().getEntityManagerFactory().createEntityManager().unwrap(Session.class);
        File file = new File("/tmp/" + c.getAttachmentName());
        byte[] fileBytes = new byte[(int) file.length()];
        try (
                InputStream inputStream = new FileInputStream(file);) {
            inputStream.read(fileBytes);

        } catch (IOException ex) {

        }

        c.setAttachment(fileBytes);
        return campainRepo.save(c);

    }

    /**
     * @return the campainRepo
     */
    public CampainRepo getCampainRepo() {

        return campainRepo;
    }

    /**
     * @param campainRepo the campainRepo to set
     */
    public void setCampainRepo(CampainRepo campainRepo) {
        this.campainRepo = campainRepo;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Campain findOne(BigDecimal campainId) {
        Campain findOne = campainRepo.findOne(campainId);
        if (findOne.getAttachment() != null) {
            byte[] docBytes = findOne.getAttachment();
            try (
                    OutputStream outFile = new FileOutputStream("/tmp/" + findOne.getAttachmentFileSystemName());) {
                int read;

                outFile.write(docBytes);

            } catch (IOException e) {

            }

        }
        return findOne;
    }

    public List<Campain> findAll() {
        return (List<Campain>) campainRepo.findAll();
    }

    void delete(BigDecimal campainId) {
        campainRepo.delete(campainId);
    }

}