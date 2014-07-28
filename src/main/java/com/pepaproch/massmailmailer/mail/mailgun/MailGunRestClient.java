/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.Item;
import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.MailgunStatus;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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

    public ResponseEntity<SentEmailResponse> sendEmail(Email e) {
        MultiValueMap formData = new MultipartMessageFactory("utf-8").getMessage(e);
        HttpHeaders headers = new HttpHeaders();

        MediaType mediaType = new MediaType("multipart", "form-data", Charset.forName("UTF-8"));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        ResponseEntity<SentEmailResponse> postForEntity = template.postForEntity("https://api.mailgun.net/v2/livetelecom.eu/messages", request, SentEmailResponse.class);
        return postForEntity;

    }

   



    public List<Item> getEvents(Date beginDate, Date endDate) {

        StringBuilder queryParamsHolder = new StringBuilder("?");
        Map<String, String> queryParams = new HashMap(4);
        queryParams.put("begin", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).format(beginDate));
        queryParams.put("end", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).format(endDate));
//        queryParams.put("ascending", "no");
        queryParams.put("limit", "100");
        queryParams.put("pretty", "no");
        Boolean first = Boolean.TRUE;
        for (String queryParam : queryParams.keySet()) {
            if (!first) {
                queryParamsHolder.append("&");
            } else {
                first = Boolean.FALSE;
            }

            queryParamsHolder.append(queryParam).append("={").append(queryParam).append("}");
        }
        ResponseEntity<MailgunStatus> mailStatus;

        mailStatus = template.getForEntity("https://api.mailgun.net/v2/livetelecom.eu/events" + queryParamsHolder.toString(), MailgunStatus.class, queryParams);
        if (mailStatus.getBody().getPaging().getNext() != null) {
            return getAllPages(mailStatus.getBody().getItems(), mailStatus.getBody().getPaging().getNext());

        } else {
            return mailStatus.getBody().getItems();
        }
    }

    private List<Item> getAllPages(List<Item> items, String nextPage) {
        ResponseEntity<MailgunStatus> mailStatus;
        mailStatus = template.getForEntity(nextPage, MailgunStatus.class);
        if (mailStatus.getBody().getItems().isEmpty()) {
            return items;
        } else {
            items.addAll(mailStatus.getBody().getItems());
            getAllPages(items, mailStatus.getBody().getPaging().getNext());
        }

        return items;
    }

    void sendEmail(MultipartEmailMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
