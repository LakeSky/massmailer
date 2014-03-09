/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.MailgunStatus;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pepa
 */
@Component
public class MailGunRestClient {

    private final RestTemplate template;

    @Autowired
    private EmailRepo emailrepo;

    @Autowired
    public MailGunRestClient(RestTemplate template) {
        this.template = template;
    }

    public void sendEmail() {

        ResponseEntity<SentEmailResponse> postForEntity = template.postForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/messages", request, SentEmailResponse.class);
        System.out.println(postForEntity);

    }

    public void sendEmail(Email e) {
        try {
            MultiValueMap formData = new LinkedMultiValueMap();
            formData.add("from", e.getFromEmail());
            formData.add("to", e.getRecipients());
   
      
        HttpHeaders htmlHeaders = new HttpHeaders();
        htmlHeaders.setContentType(new MediaType("plain", "html", Charset.forName("UTF-8")));

        HttpEntity<String> subject = new HttpEntity(e.getSubject(), htmlHeaders);
        formData.add("subject", subject);


        HttpEntity<String> html = new HttpEntity(e.getEmailText(), htmlHeaders);
        formData.add("html", html);

    
            
            

            Resource attachment = addAttachment(e.getAttachment());
            formData.add("attachment", attachment);

            HttpHeaders headers = new HttpHeaders();

            MediaType mediaType = new MediaType("multipart", "form-data", Charset.forName("UTF-8"));

            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

            ResponseEntity<SentEmailResponse> postForEntity = template.postForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/messages", request, SentEmailResponse.class);
            System.out.println(postForEntity);
            e.setSentDate(new Date());
            e.setStatusDate(e.getSentDate());
            e.setEmailStatus(postForEntity.getBody().getMessage());
            emailrepo.save(e);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MailGunRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Resource addAttachment(Attachment at) throws FileNotFoundException {
        File f = new File("/tmp/" + at.getId() + at.getAttachmentName() + ".pdf");
        try (
                FileOutputStream fos = new FileOutputStream(f);) {

            fos.write(at.getAttachment());
        } catch (IOException ex) {

        }

        return new FileSystemResource(f);

    }

    public MailgunStatus getEvents() {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        queryParams.add("begin", "Thu, 06 Mar 2014 09:02:00 GMT");
        queryParams.add("ascending", "yes");
        queryParams.add("limit", 1);
        queryParams.add("pretty", "no");
        ResponseEntity<MailgunStatus> mailStatus = template.getForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/events", MailgunStatus.class, queryParams);
        return mailStatus.getBody();

    }

    /**
     * @return the emailrepo
     */
    public EmailRepo getEmailrepo() {
        return emailrepo;
    }

    /**
     * @param emailrepo the emailrepo to set
     */
    public void setEmailrepo(EmailRepo emailrepo) {
        this.emailrepo = emailrepo;
    }

    void sendEmail(MultipartEmailMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
