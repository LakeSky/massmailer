/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import java.util.Collection;

/**
 *
 * @author pepa
 */
public interface EmailBase {

    /**
     * @return the attachments
     */
    Collection<Attachment> getAttachments();

    /**
     * @return the emailText
     */
    String getEmailText();

    String getRecipients();

}
