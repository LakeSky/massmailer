/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document(collection = "datasources")
public class DataSource {
    @Id
    private  String id;
    @Basic(optional = false)
    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    
    private DataStructure dataStructure;
    
    private Integer recordsCount;
    
    @Transient
    private Boolean fileUploaded;
    
    
 



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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the dataStructure
     */
    public DataStructure getDataStructure() {
        return dataStructure;
    }

    /**
     * @param dataStructure
     */
    public void setDataStructure(DataStructure dataStructure) {
        this.dataStructure = dataStructure;
    }

    /**
     * @return the recordsCount
     */
    public Integer getRecordsCount() {
        return (recordsCount==null)? 0 : recordsCount;
    }

    /**
     * @param recordsCount the recordsCount to set
     */
    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
    }

    /**
     * @return the fileUploaded
     */
    public Boolean getFileUploaded() {
        return (fileUploaded==null)? Boolean.FALSE : fileUploaded ;
    }

    /**
     * @param fileUploaded the fileUploaded to set
     */
    public void setFileUploaded(Boolean fileUploaded) {
        this.fileUploaded = fileUploaded;
    }





    
}
