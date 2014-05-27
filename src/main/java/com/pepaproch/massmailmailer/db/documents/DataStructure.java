/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import java.util.Collection;
import javax.persistence.Transient;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document
public class DataStructure implements CommonVisitable<DataStructure>{
    
    private String fileName;

    public DataStructure() {
    }

   @Valid
    private Collection<DataStructureMetaField> dataStructureFields; 
    
    @Transient
    private Collection<DataSourceRow> previewRows;

    public DataStructure(Collection<DataStructureMetaField> dataStructureFields) {
        this.dataStructureFields = dataStructureFields;
    }

    /**
     * @return the dataStructureFields
     */
    public Collection<DataStructureMetaField> getDataStructureFields() {
        return dataStructureFields;
    }

    /**
     * @param dataStructureFields the dataStructureFields to set
     */
    public void setDataStructureFields(Collection<DataStructureMetaField> dataStructureFields) {
        this.dataStructureFields = dataStructureFields;
    }

    /**
     * @return the previewRows
     */
    public Collection<DataSourceRow> getPreviewRows() {
        return previewRows;
    }

    /**
     * @param previewRows the previewRows to set
     */
    public void setPreviewRows(Collection<DataSourceRow> previewRows) {
        this.previewRows = previewRows;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void visit(CommonVisitor<DataStructure> t) {
       
        t.visit(this);
    }


    


  
    
}
