/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import java.util.Collection;

/**
 *
 * @author pepa
 * @param <T>
 */
public interface MailMessage<T> {
public String getRecipeient() ;
public String getFrom();
public String getText();
public String getHtml();
public Collection<Attachment> getAttachments();
}
