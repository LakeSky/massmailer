/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import com.pepaproch.massmailmailer.poi.SourceRow;
import java.util.Collection;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document(collection = "datasourcesrows")
public class DataSourceRow implements SourceRow{
    @Id
    private String id;
    
    private String dataSourceId;
    
    private Collection<DataSourceField> dataSourceFields;

    public DataSourceRow(String dataSourceId, Collection<DataSourceField> dataSourceFields) {
        this.dataSourceId = dataSourceId;
        this.dataSourceFields = dataSourceFields;
    }



    /**
     * @return the dataSourceFileld
     */
    public Collection<DataSourceField> getDataSourceFields() {
        return dataSourceFields;
    }

    /**
     * @param dataSourceFields
     */
    public void setDataSourceField(Collection<DataSourceField> dataSourceFields) {
        this.dataSourceFields = dataSourceFields;
    }

    /**
     * @return the dataSourceId
     */
    public String getDataSourceId() {
        return dataSourceId;
    }

    /**
     * @param dataSourceId the dataSourceId to set
     */
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
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


    
}
