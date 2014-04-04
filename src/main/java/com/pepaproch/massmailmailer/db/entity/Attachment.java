/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author pepa
 */
@Entity
@Table(name = "ATTACHMENT")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private BigDecimal id;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "ATTACHMENT")
    private byte[] attachment;
    @Column(name = "ATTACHMENT_NAME")
    private String attachmentName;
    @Column(name = "ATTACHMENT_FILE_TYPE")
    private String attachmentFileType;

    @ManyToOne
    @JoinColumn(name = "EMAIL_ID")
    private Email email;

    public Attachment() {
    }

    public Attachment(byte[] b, String fileName, String fileType,Email email) {
        this.attachment = b;
        this.attachmentName = fileName;
        this.attachmentFileType = fileType;
        this.email = email;
    }

    /**
     * @return the attachmentName
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * @param attachmentName the attachmentName to set
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return the attachment
     */
    public byte[] getAttachment() {
        return attachment;
    }

    /**
     * @param attachment the attachment to set
     */
    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    /**
     * @return the attachmentFileType
     */
    public String getAttachmentFileType() {
        return attachmentFileType;
    }

    /**
     * @param attachmentFileType the attachmentFileType to set
     */
    public void setAttachmentFileType(String attachmentFileType) {
        this.attachmentFileType = attachmentFileType;
    }

}
