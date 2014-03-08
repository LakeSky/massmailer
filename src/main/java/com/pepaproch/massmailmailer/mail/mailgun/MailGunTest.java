/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author pepa
 */
public class MailGunTest {

    public static ClientResponse SendSimpleMessage() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-4gejiublrcrau0mnopgtae272ca5hof1"));
        WebResource webResource
                = client.resource("https://api.mailgun.net/v2/sandbox12540.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox12540.mailgun.org>");
        formData.add("to", "Josef Procházka <pepaproch@gmail.com>");
        formData.add("subject", "Hello Josef Proížíážíýčážřcházka");
        formData.add("text", "Congratulations Josef Procházkaížáýřáýříýžážřýřčšýřč, you just sent an email with Mailgun!  You are truly awesome!  You can see a record of this email in your logs: https://mailgun.com/cp/log .  You can send up to 300 emails/day from this sandbox server.  Next, you should add your own domain so you can send 10,000 emails/month for free.");
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
    }

    public static String getStatus() {
        Client client = new Client();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-4gejiublrcrau0mnopgtae272ca5hof1"));
        WebResource webResource
                = client.resource("https://api.mailgun.net/v2/sandbox12540.mailgun.org/events");
        MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
        queryParams.add("begin", 50);
        queryParams.add("ascending", "yes");
        queryParams.add("limit", 1);
        queryParams.add("pretty", "no");
        String s = webResource.queryParams(queryParams).get(String.class);

        return s;
    }

}
