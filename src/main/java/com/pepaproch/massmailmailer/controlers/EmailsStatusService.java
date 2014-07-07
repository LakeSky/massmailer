/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.db.entity.EmailStatus;
import com.pepaproch.massmailmailer.db.entity.StatusHistory;
import com.pepaproch.massmailmailer.mail.mailgun.MailGunRestClient;
import com.pepaproch.massmailmailer.mail.mailgun.MailgunStatus.Item;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import com.pepaproch.massmailmailer.repository.EmailStatusRepo;
import com.pepaproch.massmailmailer.repository.StatusHistoryRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class EmailsStatusService {

    @Autowired
    private EmailRepo emailrepo;

    @Autowired
    private StatusHistoryRepo statusHistoryRepo;

    @Autowired
    private EmailStatusRepo emailStatusRepo;
    @Autowired
    private MailGunRestClient mailgunRestClient;

    public void updateStatuses() {
        Date startDate = getStatusHistoryRepo().getLastStatusDate();
        if (startDate == null) {
            startDate = DateTime.now().minusDays(10).toDate();
        }
        Date endDate = new Date();
        List<Item> events = getMailgunRestClient().getEvents(startDate, endDate);
        if (!events.isEmpty()) {
            HashMap<Email, Map<String, Item>> emailAndStatus = parseStatus(events);
            StatusHistory sh = new StatusHistory( persistStatus(emailAndStatus),endDate);
            getStatusHistoryRepo().save(sh);
            
           
        }

    }

    private HashMap<Email, Map<String, Item>> parseStatus(List<Item> events) {
        HashMap<Email, Map<String, Item>> emailAndStatus = new HashMap<>();
        for (Item i : events) {
            Email e = getEmailrepo().findByMessageId(i.getMessage().getHeaders().getMessage_id());
            if (e != null) {
                if (emailAndStatus.containsKey(e)) {
                    emailAndStatus.get(e).put(i.getEvent(), i);
                } else {
                    Map<String, Item> eEvents = new HashMap<String, Item>();
                    eEvents.put(i.getEvent(), i);
                    emailAndStatus.put(e, eEvents);

                }
            }

        }

        return emailAndStatus;

    }

    private int persistStatus(HashMap<Email, Map<String, Item>> emailAndStatus) {
        Comparator<Item> comp = new Comparator<Item>() {

            @Override
            public int compare(Item t, Item t1) {
                return t.getTimestampAsDate().compareTo(t1.getTimestampAsDate());
            }
        };
        int rc = 0;
        for (Email e : emailAndStatus.keySet()) {
    
      
          List<Item> events = new ArrayList<>(emailAndStatus.get(e).values());
            
            Collections.sort(events, comp);
            Iterator<Item> it = events.iterator();
            EmailStatus lastStatus = null;
            while (it.hasNext()) {
                Item i = it.next();
                EmailStatus s = new EmailStatus(i.getTimestampAsDate(), i.getEvent(), e);
                lastStatus = emailStatusRepo.save(s);
                rc++;

            }
            if (lastStatus != null) {
                e.setStatusDate(lastStatus.getStatusDate());
                e.setEmailStatus(lastStatus.getStatus());
            }
            emailrepo.save(e);
        }
        return rc;
    }

    /**
     * @return the emailrepo
     */
    public EmailRepo getEmailrepo() {
        return emailrepo;
    }

    /**
     * @param emailrepo the emailrepo to set
     */
    public void setEmailrepo(EmailRepo emailrepo) {
        this.emailrepo = emailrepo;
    }

    /**
     * @return the statusHistoryRepo
     */
    public StatusHistoryRepo getStatusHistoryRepo() {
        return statusHistoryRepo;
    }

    /**
     * @param statusHistoryRepo the statusHistoryRepo to set
     */
    public void setStatusHistoryRepo(StatusHistoryRepo statusHistoryRepo) {
        this.statusHistoryRepo = statusHistoryRepo;
    }

    /**
     * @return the mailgunRestClient
     */
    public MailGunRestClient getMailgunRestClient() {
        return mailgunRestClient;
    }

    /**
     * @param mailgunRestClient the mailgunRestClient to set
     */
    public void setMailgunRestClient(MailGunRestClient mailgunRestClient) {
        this.mailgunRestClient = mailgunRestClient;
    }

    /**
     * @return the emailStatusRepo
     */
    public EmailStatusRepo getEmailStatusRepo() {
        return emailStatusRepo;
    }

    /**
     * @param emailStatusRepo the emailStatusRepo to set
     */
    public void setEmailStatusRepo(EmailStatusRepo emailStatusRepo) {
        this.emailStatusRepo = emailStatusRepo;
    }

}
