/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author pepa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class DeliveryStatus {
    private String message;
    private String code;
    private String description;
        @JsonProperty("session-seconds")
    private Long sessionSeconds;

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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the sessionSeconds
     */
    public Long getSessionSeconds() {
        return sessionSeconds;
    }

    /**
     * @param sessionSeconds the sessionSeconds to set
     */
    public void setSessionSeconds(Long sessionSeconds) {
        this.sessionSeconds = sessionSeconds;
    }
}
