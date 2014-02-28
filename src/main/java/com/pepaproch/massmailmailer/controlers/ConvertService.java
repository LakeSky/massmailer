/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class ConvertService {

    OfficeManager officeManager;

    @PersistenceContext
    private EntityManager entityManager;

    public ConvertService() {

    }

    public void convert(String inFileName, String outFileName, Boolean removeInput) {

        File inputFile = new File(inFileName);
        try {

            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            converter.convert(inputFile, new File(outFileName));

        } finally {
            if (removeInput) {
                boolean delete = inputFile.delete();
            }

        }

    }

    public void startManager() {
        if (officeManager == null) {
            officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
        } else {
            officeManager.stop();
        }
        System.out.println("STARTING OFFICE MANAGER");
        officeManager.start();

    }

    public void stopManager() {
        System.out.println("STOPPING OFFICE MANAGER");
        officeManager.stop();
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

}
