/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import com.pepaproch.massmailmailer.db.entity.Email;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

public class MultipartMessageFactory implements MessageFactory<MultipartEmailMessage> {
    
    private final HttpHeaders htmlPartHeaders;
    private final HttpHeaders plainTextPartHeaders;
    
    public MultipartMessageFactory(String charsetString) {
        Charset charset = Charset.forName(charsetString);
        HttpHeaders htmlH = new HttpHeaders();
        htmlH.setContentType(new MediaType("plain", "html", charset));
        this.htmlPartHeaders = htmlH;
        HttpHeaders pH = new HttpHeaders();
        pH.setContentType(new MediaType("plain", "text", charset));
        plainTextPartHeaders = pH;
    }
    
    @Override
    public MultipartEmailMessage getMessage(Email email) {
        MultiValueMap<String, Object> form = new MultipartEmailMessage();
        setEnvelope(email, form);
        setContentInlined(email, form);
        addAttaChment(email, form, "attachment");
        return (MultipartEmailMessage) form;
        
    }
    
    private void setEnvelope(Email e, MultiValueMap<String, Object> form) {
        HttpEntity<String> from = new HttpEntity("infoline <postmaster@livetelecom.eu>", plainTextPartHeaders);
        form.add("from", from);
        HttpEntity<String> to = new HttpEntity(e.getRecipients(), plainTextPartHeaders);
        form.add("to", to);
        HttpEntity<String> subject = new HttpEntity(e.getSubject(), plainTextPartHeaders);
        form.add("subject", subject);
        
    }
    
    private void setContent(Email e, MultiValueMap<String, Object> form) {
        HttpEntity<String> html = new HttpEntity(e.getEmailText(), htmlPartHeaders);
        form.add("html", html);
    }
    
    private void addAttaChment(Email e, MultiValueMap<String, Object> form, String type) {
        
        for (Attachment at : e.getAttachments()) {
            form.add(type, new AttachmenrResource(at.getAttachment(), at.getAttachmentName()));
        }
        
    }
    
    
    //TODO consider to using some html/xml library
    private void setContentInlined(Email e, MultiValueMap<String, Object> form) {
        Pattern pimg = Pattern.compile("<\\s?img.*src=\"([^\\\"]*)\\\"[^/]*/>");
        Pattern psrc = Pattern.compile("src=\"([^\\\"]*),([^\\\"]*)");
        Matcher m = pimg.matcher(e.getEmailText());
        StringBuffer sb = new StringBuffer();
        int i = 1;
        while (m.find()) { 
            Matcher msrc = psrc.matcher(m.group());
            StringBuffer sbsrc = new StringBuffer();
            while (msrc.find()) {
                //skip global match
                msrc.appendReplacement(sbsrc, "src=\"cid:" + i + "_file." + m.group(1).substring(m.group(1).indexOf("/") + 1, m.group(1).indexOf(";")) + "\"");
                form.add("inline", new AttachmenrResource(Base64.decode(msrc.group(2)), +i + "_file." + m.group(1).substring(m.group(1).indexOf("/") + 1, m.group(1).indexOf(";"))));
            }
            msrc.appendTail(sbsrc);
            m.appendReplacement(sb, sbsrc.toString());
            i++;
        }
        m.appendTail(sb);
        HttpEntity<String> html = new HttpEntity(sb.toString(), htmlPartHeaders);
        form.add("html", html);
        
    }
    
}
