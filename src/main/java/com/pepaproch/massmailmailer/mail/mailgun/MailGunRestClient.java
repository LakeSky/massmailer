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

        HttpHeaders headers = createHeaders("api", "key-4gejiublrcrau0mnopgtae272ca5hof1");
   


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(queryParams, headers);

        ResponseEntity<MailgunStatus> postForEntity = template.getForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/events", MailgunStatus.class, request);
        return postForEntity.getBody();

    }
    
HttpHeaders createHeaders( final String username, final String password ){
   return new HttpHeaders(){
      {
         String auth = username + ":" + password;
         byte[] encodedAuth = Base64.encodeBase64( 
            auth.getBytes(Charset.forName("US-ASCII")) );
         String authHeader = "Basic " + new String( encodedAuth );
         set( "Authorization", authHeader );
      }
   };
}

}
