/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pepa
 */
@Entity
@Table(name = "DATA_STRUCTURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataStructure.findAll", query = "SELECT d FROM DataStructure d"),
    @NamedQuery(name = "DataStructure.findById", query = "SELECT d FROM DataStructure d WHERE d.id = :id"),
    @NamedQuery(name = "DataStructure.findByFirstRowCnames", query = "SELECT d FROM DataStructure d WHERE d.firstRowCnames = :firstRowCnames")})
public class DataStructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRST_ROW_CNAMES")
    private Boolean firstRowCnames;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dataStructureId")
    @Valid
    private Collection<DataStructureFields> dataStructureFieldsCollection;
    
    


    public DataStructure() {
    }

    public DataStructure(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFirstRowCnames() {
        return firstRowCnames;
    }

    public void setFirstRowCnames(Boolean firstRowCnames) {
        this.firstRowCnames = firstRowCnames;
    }

    @XmlTransient
    
    public Collection<DataStructureFields> getDataStructureFieldsCollection() {
        return dataStructureFieldsCollection;
    }

    public void setDataStructureFieldsCollection(Collection<DataStructureFields> dataStructureFieldsCollection) {
        this.dataStructureFieldsCollection = dataStructureFieldsCollection;
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
        if (!(object instanceof DataStructure)) {
            return false;
        }
        DataStructure other = (DataStructure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pepaproch.massmailmailer.db.entity.DataStructure[ id=" + id + " ]";
    }

 
    
}
