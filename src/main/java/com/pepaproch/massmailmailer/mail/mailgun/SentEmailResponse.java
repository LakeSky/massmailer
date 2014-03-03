/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mail.mailgun;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pepa
 */
@XmlRootElement
public class SentEmailResponse {
    private String message;
    private String id;

    public SentEmailResponse() {
    }

    public SentEmailResponse(String message, String id) {
        this.message = message;
        this.id = id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
