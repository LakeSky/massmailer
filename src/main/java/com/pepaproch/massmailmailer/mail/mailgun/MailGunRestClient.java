/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.MailgunStatus;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author pepa
 */
@Component
public class MailGunRestClient {

    private final RestTemplate template;

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
        formData.add("html", "<p><strong>Header test</strong></p>\n" +
"\n" +
"<p><img  src='data:image/jpeg;base64,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' style=\"border:0px solid black; float:right; height:70px; margin:0px; width:100px\" /></p>\n" +
"\n" +
"<p>dadfafafdafadf sdf</p>\n" +
"\n" +
"<p>FCA</p>");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        ResponseEntity<SentEmailResponse> postForEntity = template.postForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/messages", request, SentEmailResponse.class);
        System.out.println(postForEntity);

    }

    public MailgunStatus getEvents() {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        queryParams.add("begin", 50);
        queryParams.add("ascending", "yes");
        queryParams.add("limit", 1);
        queryParams.add("pretty", "no");
        ResponseEntity<MailgunStatus> mailStatus  = template.getForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/events", MailgunStatus.class, queryParams);
        return mailStatus.getBody();

    }
    


}
