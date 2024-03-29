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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author pepa
 */
@Entity
@Table(name = "CAMPAIN")
public class Campain implements Serializable {

    public final static String STATUS_EDIT = "EDIT";
    public final static String STATUS_SENDING = "SENDING";
    public final static String STATUS_INPROGRES = "INPROGRES";
    public final static String STATUS_FINISHED = "FINISHED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
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
    @Type(type = "org.hibernate.type.StringClobType")
    private String emailText;
    @Column(name = "DATASOURCE_ID")
    private String dataSourceId;


    
    @Column(name = "CUSTOMIZE_EMAIL")
    private Boolean customizeEmail;

    @Column(name = "RECORDS_COUNT")
    private Long recordsCount;

    @Column(name = "RECORDS_SENT")
    private Long recordsSent;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "campain", cascade = CascadeType.ALL, fetch = javax.persistence.FetchType.EAGER)
    private Collection<CampainAttachment> campainAttachments;

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
     * @return the recordsCount
     */
    public Long getRecordsCount() {
        return (null == recordsCount) ? 0L : recordsCount;
    }

    /**
     * @param recordsCount the recordsCount to set
     */
    public void setRecordsCount(Long recordsCount) {
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
     * @return the campainAttachments
     */
    public Collection<CampainAttachment> getCampainAttachments() {
        return campainAttachments;
    }

    /**
     * @param campainAttachments the campainAttachments to set
     */
    public void setCampainAttachments(Collection<CampainAttachment> campainAttachments) {

        this.campainAttachments = campainAttachments;
    }

    /**
     * @return the recordsSent
     */
    public Long getRecordsSent() {
        return recordsSent;
    }

    /**
     * @param recordsSent the recordsSent to set
     */
    public void setRecordsSent(Long recordsSent) {
        this.recordsSent = recordsSent;
    }

}
