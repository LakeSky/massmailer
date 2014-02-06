/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import javax.persistence.Basic;
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
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

   private DataStructureMeta dataStructureInfo;
   


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
     * @return the dataStructureInfo
     */
    public DataStructureMeta getDataStructureInfo() {
        return dataStructureInfo;
    }

    /**
     * @param dataStructureInfo the dataStructureInfo to set
     */
    public void setDataStructureInfo(DataStructureMeta dataStructureInfo) {
        this.dataStructureInfo = dataStructureInfo;
    }



    
}
