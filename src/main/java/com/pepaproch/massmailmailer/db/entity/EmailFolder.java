/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author honzaf
 */
@Entity
@Table(name = "EMAIL_FOLDER")
@NamedQueries({
    @NamedQuery(name = "EmailFolder.findAll", query = "SELECT e FROM EmailFolder e"),
    @NamedQuery(name = "EmailFolder.findById", query = "SELECT e FROM EmailFolder e WHERE e.id = :id")})
public class EmailFolder implements Serializable {
     public final static Long FOLDER_INBOX = 1L;
    
    public final static Long FOLDER_OUTBOX = 2L;
    public final static Long FOLDER_DRAFTS = 3L;
    public final static Long FOLDER_OUTGOING = 4L;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    
    private String name;
    
    
    @OneToOne
    private EmailFolder parentFolder;
    


    @OneToMany
    private List<Email> emails;

    public EmailFolder() {
       
    }

    public EmailFolder(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof EmailFolder)) {
            return false;
        }
        EmailFolder other = (EmailFolder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pepaproch.massmailmailer.db.entity.EmailFolder[id=" + id + "]";
    }

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
     * @return the parentFolder
     */
    public EmailFolder getParentFolder() {
        return parentFolder;
    }

    /**
     * @param parentFolder the parentFolder to set
     */
    public void setParentFolder(EmailFolder parentFolder) {
        this.parentFolder = parentFolder;
    }

    /**
     * @return the email
     */
    public List<Email> getEmails() {
        return emails;
    }

    /**
     * @param email the email to set
     */
    public void setEmails(List<Email> email) {
        this.emails = email;
    }

}
