/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 * @author pepa
 */
public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {
    
private final UsernamePasswordCredentials credentials;

    public BasicAuthInterceptor(UsernamePasswordCredentials credentials) {
        this.credentials = credentials;
    }
    
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        addAuthHeader(request);
           return execution.execute( request, body );
    }

    private void addAuthHeader(HttpRequest request) {
         request.getHeaders().add( "Authorization","Basic " + encodeAuth(credentials.getUserName(), credentials.getPassword()) );
    }

    private String encodeAuth(String userName, String password) {
        final String authString = userName + ":" + password;
        return Base64.encodeBase64String(authString.getBytes());
    }

}
