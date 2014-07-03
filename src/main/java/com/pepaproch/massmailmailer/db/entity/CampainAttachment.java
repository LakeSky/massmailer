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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author pepa
 */
@Entity
@Table(name = "CAMPAIN_ATTACHMENT")
public class CampainAttachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "INDEX")
    private Long index;

    @JsonIgnore
    @JoinColumn(name = "CAMPAIN_ID")
    @ManyToOne
    private Campain campain;

    @Lob
    @Column(name = "ATTACHMENT")
    @JsonIgnore
    private byte[] attachment;

    @Column(name = "ATTACHMENT_NAME")
    private String attachmentName;

    @Column(name = "ATTACHMENT_FS_NAME")
    private String attachmentFileSystemName;

    @Column(name = "ATTACHMENT_FILE_TYPE")
    private String attachmentFileType;
    
    @Column(name = "ATTACHMENT_OUTPUT_NAME")
    private String attachmentOutputName;
    @Column(name = "ATTACHMENT_OUTPUT_TYPE")
    private String attachmentOutputType;

    @Column(name = "CUSTOMIZE_ATTACHMENT")
    private Boolean customizeAttachments;
    @Column(name = "SENT_ATTACHMENT_AS")
    private String sentAttachemntAs;

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
     * @return the customizeAttachments
     */
    public Boolean getCustomizeAttachments() {
        return (customizeAttachments == null) ? java.lang.Boolean.FALSE : customizeAttachments;
    }

    /**
     * @param customizeAttachments the customizeAttachments to set
     */
    public void setCustomizeAttachments(Boolean customizeAttachments) {
        this.customizeAttachments = customizeAttachments;
    }

    /**
     * @return the sentAttachemntAs
     */
    public String getSentAttachemntAs() {
        return sentAttachemntAs;
    }

    /**
     * @param sentAttachemntAs the sentAttachemntAs to set
     */
    public void setSentAttachemntAs(String sentAttachemntAs) {
        this.sentAttachemntAs = sentAttachemntAs;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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

    /**
     * @return the attachmentOutputName
     */
    public String getAttachmentOutputName() {
        return attachmentOutputName;
    }

    /**
     * @param attachmentOutputName the attachmentOutputName to set
     */
    public void setAttachmentOutputName(String attachmentOutputName) {
        this.attachmentOutputName = attachmentOutputName;
    }

    /**
     * @return the attachmentOutputType
     */
    public String getAttachmentOutputType() {
        return attachmentOutputType;
    }

    /**
     * @param attachmentOutputType the attachmentOutputType to set
     */
    public void setAttachmentOutputType(String attachmentOutputType) {
        this.attachmentOutputType = attachmentOutputType;
    }

    /**
     * @return the attachmentFileSystemName
     */
    public String getAttachmentFileSystemName() {
        return attachmentFileSystemName;
    }

    /**
     * @param attachmentFileSystemName the attachmentFileSystemName to set
     */
    public void setAttachmentFileSystemName(String attachmentFileSystemName) {
        this.attachmentFileSystemName = attachmentFileSystemName;
    }

    /**
     * @return the campain
     */
    public Campain getCampain() {
        return campain;
    }

    /**
     * @param campain the campain to set
     */
    public void setCampain(Campain campain) {
        this.campain = campain;
    }

    /**
     * @return the index
     */
    public Long getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Long index) {
        this.index = index;
    }

}
