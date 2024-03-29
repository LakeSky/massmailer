package com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;


@Generated("com.googlecode.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private List<Object> tags = new ArrayList<Object>();
    private Double timestamp;
    private Envelope envelope;
    private String event;
    @JsonProperty(value = "delivery-status")
    private DeliveryStatus deliveryStastus;
    private List<Object> campaigns = new ArrayList<Object>();

    @JsonProperty("user-variables")
    private User_variables user_variables;
    private Flags flags;
    private Message message;
    private String recipient;
    private String method;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<Object> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Object> campaigns) {
        this.campaigns = campaigns;
    }

    public User_variables getUser_variables() {
        return user_variables;
    }

    public void setUser_variables(User_variables user_variables) {
        this.user_variables = user_variables;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * @return the deliveryStastus
     */
    public DeliveryStatus getDeliveryStastus() {
        return deliveryStastus;
    }

    /**
     * @param deliveryStastus the deliveryStastus to set
     */
    public void setDeliveryStastus(DeliveryStatus deliveryStastus) {
        this.deliveryStastus = deliveryStastus;
    }

    public Date getTimestampAsDate() {
        if (getTimestamp() != null) {
            Double d = getTimestamp() * 1000;
            return (new Date(d.longValue()));
        } else {
            return null;
        }

    }

}
