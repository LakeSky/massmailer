/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author pepa
 */
@Entity
@Table(name = "EMAIL")
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e"),
    @NamedQuery(name = "Email.findById", query = "SELECT e FROM Email e WHERE e.id = :id")})
public class Email implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private BigDecimal id;
    private String fromEmail;
    private String recipients;
    private String ccRecipients;
    @Column(name = "BCC_RECIPIENT")
    private String bccRecipients;
    @Column(name = "SUBJECT")
    private String subject;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="EMAIL_CONTENT")
    private EmailContent emailContent;
    @Column(name = "SENT_DATE")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date sentDate;
    @JoinColumn(name = "EMAIL_FOLEDER")
    @ManyToOne
    private EmailFolder emailFolder;
    @Transient
    private Boolean readyToSent;
    @Lob
    @Column(name = "ATTACHMENT")
    private Serializable attachment;
    @Column(name = "ATTACHMENT_NAME")
    private String attachmentName;

    public Email() {
    }

    public Email(BigDecimal id) {
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
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.expreska.online.entity.EmailHeader[id=" + id + "]";
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
     * @return the emailBody
     */
    public EmailContent getEmailBody() {
        return emailContent;
    }

    /**
     * @param emailContent
     */
    public void setEmailBody(EmailContent emailContent) {
        this.emailContent = emailContent;
    }



    /**
     * @return the sentDate
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * @param sentDate the sentDate to set
     */
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }



    /**
     * @return the readyToSent
     */
    public Boolean getReadyToSent() {
        return readyToSent;
    }

    /**
     * @param readyToSent the readyToSent to set
     */
    public void setReadyToSent(Boolean readyToSent) {
        this.readyToSent = readyToSent;
    }



    /**
     * @return the emailFolder
     */
    public EmailFolder getEmailFolder() {
        return emailFolder;
    }

    /**
     * @param emailFolder the emailFolder to set
     */
    public void setEmailFolder(EmailFolder emailFolder) {
        this.emailFolder = emailFolder;
    }

    /**
     * @return the attachment
     */
    public Serializable getAttachment() {
        return attachment;
    }

    /**
     * @param attachment the attachment to set
     */
    public void setAttachment(Serializable attachment) {
        this.attachment = attachment;
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
     * @return the fromEmail
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * @param fromEmail the fromEmail to set
     */
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }



}
