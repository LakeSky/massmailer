/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Email;
import java.util.Collection;

/**
 *
 * @author pepa
 */
public interface MailGateway {
    public void sendEmail(Email email);
    public void sendEmailBatch(Collection<Email> emails);
}
