/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Email;
import java.util.Collection;

public class MailGatewayMailgun implements MailGateway {

    private MailGunRestClient client;

    @Override
    public void sendEmail(Email email) {
        MessageFactory<MultipartEmailMessage> factory = new MultipartMessageFactory();
        MultipartEmailMessage message = factory.getMessage(email);
        client.sendEmail(message);
    }

    @Override
    public void sendEmailBatch(Collection<Email> emails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
