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
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author pepa
 */
@Entity
@Table(name = "CAMPAIN")
public class Campain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private BigDecimal id;
    @Column(name = "CAMPAIN_NAME")
    private String campainName;
    @Column(name = "RECIPIENTS")
    private String recipients;
    @Column(name = "CC_RECIPIENTS")
    private String ccRecipients;
    @Column(name = "BCC_RECIPIENTS")
    private String bccRecipients;
    @Column(name = "SUBJECT")
    private String subject;
    @Lob
    @Column(name = "EMAIL_TEXT")
    private String emailText;
    @Column(name = "DATASOURCE_ID")
    private String dataSourceId;
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "ATTACHMENT" )
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

    @Column(name = "CUSTOMIZE_EMAIL")
    private Boolean customizeEmail;
    @Column(name = "CUSTOMIZE_ATTACHMENT")
    private Boolean customizeAttachments;
    @Column(name = "SENT_ATTACHMENT_AS")
    private String sentAttachemntAs;

    @Column(name = "RECORDS_COUNT")
    private BigDecimal recordsCount;

    @Column(name = "STATUS")
    private String status;

    /**
     * @return the emailText
     */
    public String getEmailText() {
        return emailText;
    }

    /**
     * @param emailText the emailText to set
     */
    public void setEmailText(String emailText) {
        this.emailText = emailText;
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
     * @return the customizeEmail
     */
    public Boolean getCustomizeEmail() {
        return customizeEmail;
    }

    /**
     * @param customizeEmail the customizeEmail to set
     */
    public void setCustomizeEmail(Boolean customizeEmail) {
        this.customizeEmail = customizeEmail;
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
     * @return the campainName
     */
    public String getCampainName() {
        return campainName;
    }

    /**
     * @param campainName the campainName to set
     */
    public void setCampainName(String campainName) {
        this.campainName = campainName;
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
     * @return the recordsCount
     */
    public BigDecimal getRecordsCount() {
        return (null == recordsCount) ? BigDecimal.ZERO : recordsCount;
    }

    /**
     * @param recordsCount the recordsCount to set
     */
    public void setRecordsCount(BigDecimal recordsCount) {
        this.recordsCount = recordsCount;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the recipients
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * @param recipients the recipients to set
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    /**
     * @return the ccRecipients
     */
    public String getCcRecipients() {
        return ccRecipients;
    }

    /**
     * @param ccRecipients the ccRecipients to set
     */
    public void setCcRecipients(String ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    /**
     * @return the bccRecipients
     */
    public String getBccRecipients() {
        return bccRecipients;
    }

    /**
     * @param bccRecipients the bccRecipients to set
     */
    public void setBccRecipients(String bccRecipients) {
        this.bccRecipients = bccRecipients;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
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

}
