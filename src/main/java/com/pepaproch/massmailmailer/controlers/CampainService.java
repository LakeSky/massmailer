/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.CampainAttachment;
import com.pepaproch.massmailmailer.db.entity.CampainSpecification;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.repository.CampainAttaRepo;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class CampainService {

    @Autowired
    private CampainRepo campainRepo;
    @Autowired
    private CampainAttaRepo campainAttarepo;
    @Autowired
    private DataSourceRowsRep rowsRepository;

    public Campain save(Campain c) throws FileNotFoundException {

        Collection<CampainAttachment> findByCampainId = campainAttarepo.findByCampainId(c.getId());
        if (findByCampainId != null && !findByCampainId.isEmpty()) {
            campainAttarepo.delete(findByCampainId);
        }

        Long countRows = rowsRepository.countByDataSourceId(c.getDataSourceId());
        if (c.getId().compareTo(Long.valueOf(-1)) != 0) {
        } else {
            c.setId(null);
        }
        c.setRecordsCount(countRows);
        Long i = 0L;
        for (CampainAttachment at : c.getCampainAttachments()) {
            if (at.getCampain() == null) {
                at.setCampain(c);
            }

            at.setIndex(i);
            i++;
            File file = new File("/tmp/" + at.getAttachmentFileSystemName());
            byte[] fileBytes = new byte[(int) file.length()];
            try (
                    InputStream inputStream = new FileInputStream(file);) {
                inputStream.read(fileBytes);

            } catch (IOException ex) {

            }

            at.setAttachment(fileBytes);
        }

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

    public Campain findOne(Long campainId) {
        Campain findOne = campainRepo.findOne(campainId);
        for (CampainAttachment at : findOne.getCampainAttachments()) {
            if (at.getAttachment() != null) {
                byte[] docBytes = at.getAttachment();
                try (
                        OutputStream outFile = new FileOutputStream("/tmp/" + at.getAttachmentFileSystemName());) {
                    int read;
                    outFile.write(docBytes);

                } catch (IOException e) {

                }

            }
        }

        return findOne;
    }

    public List<Campain> findAll() {
        return (List<Campain>) campainRepo.findAll();
    }

    public List<Campain> findAllByPropertyNameAndvalue(String fieldname, Object value, Pageable page) {

        return (List<Campain>) campainRepo.findAll();
    }

    void delete(Long campainId) {
        campainRepo.delete(campainId);
    }

    /**
     * @return the rowsRepository
     */
    public DataSourceRowsRep getRowsRepository() {
        return rowsRepository;
    }

    /**
     * @param rowsRepository the rowsRepository to set
     */
    public void setRowsRepository(DataSourceRowsRep rowsRepository) {
        this.rowsRepository = rowsRepository;
    }

    List<Campain> searchAll(Pageable pageSpecification, String search, String searchString, String ctype) {
        if (null != searchString) {
            return campainRepo.findAll(Specifications.where(CampainSpecification.findByCampainName(searchString, CampainSpecification.LIKE_COMPARE)).and(CampainSpecification.findByCampainType(ctype)), pageSpecification).getContent();
        } else {
            return campainRepo.findAll(CampainSpecification.findByCampainType(ctype), pageSpecification).getContent();
        }
    }

    /**
     * @return the campainAttarepo
     */
    public CampainAttaRepo getCampainAttarepo() {
        return campainAttarepo;
    }

    /**
     * @param campainAttarepo the campainAttarepo to set
     */
    public void setCampainAttarepo(CampainAttaRepo campainAttarepo) {
        this.campainAttarepo = campainAttarepo;
    }

}
