/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document       
public class DataStructureMetaField {
    private Integer index;
    private String name;

    public DataStructureMetaField(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
   
}
