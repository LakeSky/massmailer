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
@Table(name = "DATA_SOURCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataSource.findAll", query = "SELECT d FROM DataSource d"),
    @NamedQuery(name = "DataSource.findById", query = "SELECT d FROM DataSource d WHERE d.id = :id"),
    @NamedQuery(name = "DataSource.findByName", query = "SELECT d FROM DataSource d WHERE d.name = :name"),
    @NamedQuery(name = "DataSource.findByFileName", query = "SELECT d FROM DataSource d WHERE d.fileName = :fileName"),
    @NamedQuery(name = "DataSource.findByRecordsCount", query = "SELECT d FROM DataSource d WHERE d.recordsCount = :recordsCount")})
public class DataSource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "RECORDS_COUNT")
    private Integer recordsCount;

    public DataSource() {
    }

    public DataSource(Integer id) {
        this.id = id;
    }

    public DataSource(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
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
        if (!(object instanceof DataSource)) {
            return false;
        }
        DataSource other = (DataSource) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pepaproch.massmailmailer.db.entity.DataSource[ id=" + id + " ]";
    }
    
}