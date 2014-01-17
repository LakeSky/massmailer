/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.service;

import com.pepaproch.massmailmailer.db.Files;
import com.pepaproch.massmailmailer.repository.FilesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class FileService {

    @Autowired
    private FilesRepo filesR;

    /**
     * @return the userR
     */
    public FilesRepo getFilesR() {
        return filesR;
    }

    /**
     * @param filesR
     */
    public void setFilesR(FilesRepo filesR) {
        this.filesR = filesR;
    }

    public Iterable<Files> listUsers() {
        return filesR.findAll();
    }
}
