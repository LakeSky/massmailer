/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pepa
 */
@Entity
@Table(name = "DATA_STRUCTURE_FIELDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataStructureFields.findAll", query = "SELECT d FROM DataStructureFields d"),
    @NamedQuery(name = "DataStructureFields.findById", query = "SELECT d FROM DataStructureFields d WHERE d.id = :id"),
    @NamedQuery(name = "DataStructureFields.findByfileFName", query = "SELECT d FROM DataStructureFields d WHERE d.fileFName = :fileFName"),
    @NamedQuery(name = "DataStructureFields.findBydataSourceFName", query = "SELECT d FROM DataStructureFields d WHERE d.dataSourceFName = :dataSourceFName"),
    @NamedQuery(name = "DataStructureFields.findByIsId", query = "SELECT d FROM DataStructureFields d WHERE d.isId = :isId"),
    @NamedQuery(name = "DataStructureFields.findByIndex", query = "SELECT d FROM DataStructureFields d WHERE d.index = :index"),
    @NamedQuery(name = "DataStructureFields.findByType", query = "SELECT d FROM DataStructureFields d WHERE d.type = :type")})
public class DataStructureFields implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FILE_FNAME")
    private String fileFName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DATA_SOURCE_FNAME")
    private String dataSourceFName;
    @Column(name = "IS_ID")
    private Short isId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INDEX")
    private int index;
    @Size(max = 25)
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "DATA_STRUCTURE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private DataStructure dataStructureId;

    public DataStructureFields() {
    }

    public DataStructureFields(Integer id) {
        this.id = id;
    }

    public DataStructureFields(Integer id, String name, String value, int sort) {
        this.id = id;
        this.fileFName = name;
        this.dataSourceFName = value;
        this.index = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Short getIsId() {
        return isId;
    }

    public void setIsId(Short isId) {
        this.isId = isId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int sort) {
        this.index = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataStructure getDataStructureId() {
        return dataStructureId;
    }

    public void setDataStructureId(DataStructure dataStructureId) {
        this.dataStructureId = dataStructureId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataStructureFields)) {
            return false;
        }
        DataStructureFields other = (DataStructureFields) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pepaproch.massmailmailer.db.entity.DataStructureFields[ id=" + id + " ]";
    }

    /**
     * @return the dataSourceFName
     */
    public String getDataSourceFName() {
        return dataSourceFName;
    }

    /**
     * @param dataSourceFName the dataSourceFName to set
     */
    public void setDataSourceFName(String dataSourceFName) {
        this.dataSourceFName = dataSourceFName;
    }

    /**
     * @return the fileFName
     */
    public String getFileFName() {
        return fileFName;
    }

    /**
     * @param fileFName the fileFName to set
     */
    public void setFileFName(String fileFName) {
        this.fileFName = fileFName;
    }
    
}
