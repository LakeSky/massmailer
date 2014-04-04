/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import com.pepaproch.massmailmailer.db.entity.Email;
import java.nio.charset.Charset;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

public class MultipartMessageFactory implements MessageFactory<MultipartEmailMessage> {

    private final HttpHeaders htmlPartHeaders;
    private final HttpHeaders plainTextPartHeaders;

    public MultipartMessageFactory(String charsetString) {
        Charset charset = Charset.forName(charsetString);
        HttpHeaders htmlH = new HttpHeaders();
        htmlH.setContentType(new MediaType("plain", "html", charset));
        this.htmlPartHeaders = htmlH;
        HttpHeaders pH = new HttpHeaders();
        pH.setContentType(new MediaType("plain", "text", charset));
        plainTextPartHeaders = pH;
    }

    @Override
    public MultipartEmailMessage getMessage(Email email) {
        MultiValueMap<String, Object> form = new MultipartEmailMessage();
        setEnvelope(email, form);
        setContent(email, form);
        addAttaChment(email, form);

        return (MultipartEmailMessage) form;

    }

    private void setEnvelope(Email e, MultiValueMap<String, Object> form) {
        HttpEntity<String> from = new HttpEntity("Mailgun Sandbox <postmaster@sandbox12540.mailgun.org>", plainTextPartHeaders);
        form.add("from", from);
        HttpEntity<String> to = new HttpEntity(e.getRecipients(), plainTextPartHeaders);
        form.add("to", to);
        HttpEntity<String> subject = new HttpEntity(e.getSubject(), plainTextPartHeaders);

        form.add("subject", subject);
    }

    private void setContent(Email e, MultiValueMap<String, Object> form) {
        HttpEntity<String> html = new HttpEntity(e.getEmailText(), htmlPartHeaders);
        form.add("html", html);
    }

    private void addAttaChment(Email e, MultiValueMap<String, Object> form) {
       
        for(Attachment at :e.getAttachments()) {
              form.add("attachment", new AttachmenrResource(at.getAttachment(), at.getAttachmentName()));
        }
    
  

    }

}
