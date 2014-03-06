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
import java.util.Date;
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
        MultiValueMap formData = new LinkedMultiValueMap();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox12540.mailgun.org>");
        formData.add("to", "Josef Procházka <pepaproch@gmail.com>");
        formData.add("subject", "Hello Josef Procházka");
        formData.add("text", "Congratulations Josef Procházka, you just sent an email with Mailgun!  You are truly awesome!  You can see a record of this email in your logs: https://mailgun.com/cp/log .  You can send up to 300 emails/day from this sandbox server.  Next, you should add your own domain so you can send 10,000 emails/month for free.");
        formData.add("html", "<p><strong>Header test</strong></p>");

        Resource logo = new FileSystemResource("/tmp/test.doc");
        formData.add("attachment", logo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        ResponseEntity<SentEmailResponse> postForEntity = template.postForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/messages", request, SentEmailResponse.class);
        System.out.println(postForEntity);

    }

    public void sendEmail(Email e) {
        try {
            MultiValueMap formData = new LinkedMultiValueMap();
            formData.add("from", e.getFromEmail());
            formData.add("to", e.getRecipients());
            formData.add("subject", e.getSubject());
            formData.add("text", e.getEmailText().replaceAll("\\<.*?\\>", ""));
            formData.add("html", e.getEmailText());
            
            Resource attachment = addAttachment(e.getAttachment());
            formData.add("attachment", attachment);
            
            HttpHeaders headers = new HttpHeaders();
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
       File f = new  File("/tmp/" + at.getId()  + at.getAttachmentName() +".pdf");
       try(
       FileOutputStream fos = new FileOutputStream(f);
               ) {
       
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

}
