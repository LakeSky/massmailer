
package com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.googlecode.jsonschema2pojo")
public class Paging {

    private String next;
    private String previous;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
