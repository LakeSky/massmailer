/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.Email;

/**
 *
 * @author pepa
 */
public class DefaultMailRecordBulder implements MailRecordBulder {

    private final Email email;
 
    
    public DefaultMailRecordBulder() {
        this.email = new Email();
    }
    
    @Override
    public void setFrom(String s) {
        email.setFromEmail(s);
    }
    
    @Override
    public void setReccipients(String rec, String bcc, String ccr) {
        email.setRecipients(rec);
        email.setBccRecipients(bcc);
        email.setCcRecipients(ccr);
    }
    
    @Override
    public void setStatus(String s) {
        email.setEmailStatus(s);
    }
    
    @Override
    public void setEmailContent(String s) {
     email.setEmailText(s);
    }
    
    @Override
    public Email getEmail() {
        return email;
    }

    @Override
    public void setAttachment(byte[] b, String fileName, String fileType) {
        Attachment at = new Attachment( b, fileName, fileType);
        email.setAttachment(at);
    }

    @Override
    public void setCampain(Campain c) {
    email.setCampain(c);
    }

    @Override
    public void setSubject(String proccesEmailBody) {
email.setSubject(proccesEmailBody);
    }


    
}
