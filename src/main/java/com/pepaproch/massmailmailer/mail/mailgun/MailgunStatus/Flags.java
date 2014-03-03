

package com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.googlecode.jsonschema2pojo")
public class Flags {

    private Boolean is_authenticated;
    private Boolean is_system_test;
    private Boolean is_test_mode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getIs_authenticated() {
        return is_authenticated;
    }

    public void setIs_authenticated(Boolean is_authenticated) {
        this.is_authenticated = is_authenticated;
    }

    public Boolean getIs_system_test() {
        return is_system_test;
    }

    public void setIs_system_test(Boolean is_system_test) {
        this.is_system_test = is_system_test;
    }

    public Boolean getIs_test_mode() {
        return is_test_mode;
    }

    public void setIs_test_mode(Boolean is_test_mode) {
        this.is_test_mode = is_test_mode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
