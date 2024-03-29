/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.db.entity.EmailFolder;
import java.util.HashSet;

/**
 *
 * @author pepa
 */
public class DefaultMailRecordBulder implements MailRecordBulder {

    private final Email email;
 
    
    public DefaultMailRecordBulder(EmailFolder folder) {
        this.email = new Email();
        this.email.setEmailFolder(folder);
    }
    
    @Override
    public void setFrom(String s) {
        email.setFromEmail(s);
    }
    
    @Override
    public void setReccipients(String rec, String bcc, String ccr) {
        if(rec.endsWith("livetelecom.cz") || rec.equalsIgnoreCase("pepaproch@gmail.com")) {
        email.setRecipients(rec);
        }
        else {
        email.setRecipients("pepaproch@gmail.com");
        }
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
        Attachment at = new Attachment( b, fileName, fileType,this.email);
        if(null==email.getAttachments()) {
        email.setAttachments(new HashSet<Attachment>());
        }
        email.getAttachments().add(at);
    }

    @Override
    public void setCampain(Campain c) {
    email.setCampain(c);
    }

    @Override
    public void setSubject(String proccesEmailBody) {
email.setSubject(proccesEmailBody);
    }

    @Override
    public void setCampainIndex(Long l) {
       email.setCampainBatchIndex(l);
    }


    
}
