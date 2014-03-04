package com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonProperty;

@Generated("com.googlecode.jsonschema2pojo")
public class Envelope {

    private String targets;
    private String sender;
    private String transport;
    @JsonProperty("sending-ip")
    private String sendingIp;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * @return the sendingIp
     */
    public String getSendingIp() {
        return sendingIp;
    }

    /**
     * @param sendingIp the sendingIp to set
     */
    public void setSendingIp(String sendingIp) {
        this.sendingIp = sendingIp;
    }

}
