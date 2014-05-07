/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db;


import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.Email;

/**
 *
 * @author pepa
 */
public interface MailRecordBulder {
    
    public void setFrom(String s);
    public void setReccipients(String rec,String bbc ,String ccr );
    public void setStatus(String s);
    public void setEmailContent( String sceRowId);
    public void setAttachment(byte[] b, String fileName, String fileType);
    public Email getEmail();
    public void setCampain(Campain c);
    public void setSubject(String proccesEmailBody);
    public void setCampainIndex(Long l);


    
      
}
