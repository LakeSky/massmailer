/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.documents;

import com.pepaproch.massmailmailer.poi.DataType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document
public class DataStructureMetaField {

    private Integer index;
    private String name;
    private DataType dataType;

    public DataStructureMetaField(Integer index, String name, DataType dataType) {
        this.index = index;
        this.name = name;
        this.dataType = dataType;
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

    /**
     * @return the dataType
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    
    public String format(DataSourceField f) {
        return dataType.format(f.getValue());
    }

}
