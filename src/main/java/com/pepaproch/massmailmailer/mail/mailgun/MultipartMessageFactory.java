/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Email;
import java.nio.charset.Charset;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

public class MultipartMessageFactory implements MessageFactory<MultipartEmailMessage> {

    private final HttpHeaders htmlPartHeaders;
    private final HttpHeaders plainTextPartHeaders;
    private final String tempDirectory;

    public MultipartMessageFactory(Charset charset) {
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
        form.add("from", form);
        

        formData.add("from", "Mailgun Sandbox <postmaster@sandbox12540.mailgun.org>");
        formData.add("to", "Josef Procházka <pepaproch@gmail.com>");

        formData.add("text", "Congratulations Josef Procházka, you just sent an email with Mailgun!  You are truly awesome!  You can see a record of this email in your logs: https://mailgun.com/cp/log .  You can send up to 300 emails/day from this sandbox server.  Next, you should add your own domain so you can send 10,000 emails/month for free.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(formData, headers);

        return (MultipartEmailMessage) form;

    }

    private void setEnvelope(Email e, MultiValueMap<String, Object> form) {

    }

    private void setContent(Email e, MultiValueMap<String, Object> form) {

    }

    private void setHeader(Email e, MultiValueMap<String, Object> form) {

    }

    private void addAttaChment(Email e, MultiValueMap<String, Object> form) {

    }

}
