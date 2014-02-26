/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi.convert;

import java.util.Collection;

/**
 *
 * @author pepa
 */
public class TemplateMeta {
    private Collection<String> placeHolders;
    private String fileName;

    /**
     * @return the placeHolders
     */
    public Collection<String> getPlaceHolders() {
        return placeHolders;
    }

    /**
     * @param placeHolders the placeHolders to set
     */
    public void setPlaceHolders(Collection<String> placeHolders) {
        this.placeHolders = placeHolders;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

 
}
