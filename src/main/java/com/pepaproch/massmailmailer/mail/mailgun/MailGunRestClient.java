/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Attachment;
import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.Item;
import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.MailgunStatus;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
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

        ResponseEntity<SentEmailResponse> postForEntity = template.postForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/messages", request, SentEmailResponse.class);
        return postForEntity;

    }

    private Resource addAttachment(Attachment at) throws FileNotFoundException {
        File f = new File("/tmp/" + at.getId() + at.getAttachmentName() + ".pdf");
        try (
                FileOutputStream fos = new FileOutputStream(f);) {

            fos.write(at.getAttachment());
        } catch (IOException ex) {

        }

        return new FileSystemResource(f);

    }

    public List<Item> getEvents(Date beginDate) {

        StringBuilder queryParamsHolder = new StringBuilder("?");
        Map<String, String> queryParams = new HashMap(4);
        queryParams.put("begin", new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z", Locale.ENGLISH).format(beginDate));
        queryParams.put("ascending", "yes");
        queryParams.put("limit", "1");
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

        mailStatus = template.getForEntity("https://api.mailgun.net/v2/sandbox12540.mailgun.org/events" + queryParamsHolder.toString(), MailgunStatus.class, queryParams);
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
