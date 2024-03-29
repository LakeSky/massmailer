/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.documents;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document(collection = "datasources")
public class DataSource {

    @Id
    private String id;
    @Basic(optional = false)
    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    @Valid
    private DataStructure dataStructure;

    private Long recordsCount;

    private String category;
    
    private String description;
    
    private Date dateUpdated;

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
    public Long getRecordsCount() {
        return (recordsCount == null) ? 0 : recordsCount;
    }

    /**
     * @param recordsCount the recordsCount to set
     */
    public void setRecordsCount(Long recordsCount) {
        this.recordsCount = recordsCount;
    }

    /**
     * @return the fileUploaded
     */
    public Boolean getFileUploaded() {
        return (fileUploaded == null) ? Boolean.FALSE : fileUploaded;
    }

    /**
     * @param fileUploaded the fileUploaded to set
     */
    public void setFileUploaded(Boolean fileUploaded) {
        this.fileUploaded = fileUploaded;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the dateUpdated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated the dateUpdated to set
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }



}
