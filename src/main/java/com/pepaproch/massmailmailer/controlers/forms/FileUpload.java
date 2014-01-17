/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers.forms;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author pepa
 */
public class FileUpload {

    private String name = null;
    private CommonsMultipartFile file = null;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CommonsMultipartFile getFile() {
        return file;
    }
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
        this.name = file.getOriginalFilename();
    }
}