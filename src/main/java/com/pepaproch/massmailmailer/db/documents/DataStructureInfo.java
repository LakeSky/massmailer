/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import java.util.Collection;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document
public class DataStructureInfo {
    

    private Collection<DataStructureField> dataStructureFields; 

    public DataStructureInfo(Collection<DataStructureField> dataStructureFields) {
        this.dataStructureFields = dataStructureFields;
    }

    /**
     * @return the dataStructureFields
     */
    public Collection<DataStructureField> getDataStructureFields() {
        return dataStructureFields;
    }

    /**
     * @param dataStructureFields the dataStructureFields to set
     */
    public void setDataStructureFields(Collection<DataStructureField> dataStructureFields) {
        this.dataStructureFields = dataStructureFields;
    }
    
}
