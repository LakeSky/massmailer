/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author honzaf
 */
@Entity
@Table(name = "EMAIL_CONTENT")
@NamedQueries({
    @NamedQuery(name = "EmailContent.findAll", query = "SELECT e FROM EmailContent e"),
    @NamedQuery(name = "EmailContent.findById", query = "SELECT e FROM EmailContent e WHERE e.id = :id")})
public class EmailContent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private BigDecimal id;
    @Column(name = "EMAIL_VARIABLES")
    @Lob
    private String emailVariables;


    public EmailContent() {
    }

    public EmailContent(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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
        if (!(object instanceof EmailContent)) {
            return false;
        }
        EmailContent other = (EmailContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pepaproch.massmailmailer.db.entity.EmailContent[id=" + id + "]";
    }




}
