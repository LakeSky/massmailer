/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.db.entity.EmailFolder;
import com.pepaproch.massmailmailer.mail.mailgun.MailGunRestClient;
import com.pepaproch.massmailmailer.mail.mailgun.SentEmailResponse;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import com.pepaproch.massmailmailer.repository.EmailFolderRepo;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class CampainSendService{

    private final int page = 0;
    private final int size = 10;

    @Autowired
    private EmailRepo emailrepo;
    
    @Autowired
    private EmailFolderRepo emailFolderrepo;
    
    @Autowired
    private CampainRepo campainRepo;
    
    @Autowired
    private MailGunRestClient mailgunClient;


    public void send() {
        

        Collection<Campain> findByStatus = getCampainRepo().findByStatus(Campain.STATUS_INPROGRES);        

       EmailFolder folderOutbox = emailFolderrepo.getByEmailFolderId(EmailFolder.FOLDER_OUTBOX);
        for (Campain c : findByStatus) {
            Collection<Email> findUnsentPaginated = emailrepo.findUnsentPaginated(c.getId(),EmailFolder.FOLDER_OUTGOING, new PageRequest(page, size));
            for (Email e : findUnsentPaginated) {
                ResponseEntity<SentEmailResponse> status = mailgunClient.sendEmail(e);
                System.out.println(status);
                e.setSentDate(new Date());
                e.setEmailFolder(folderOutbox);
                e.setStatusDate(e.getSentDate());
                e.setEmailStatus(status.getBody().getMessage());
                
                e.setMessageId(status.getBody().getId().substring(1, status.getBody().getId().length()-1));
                emailrepo.save(e);
            }
            updateCampainStatus(c);
        }

    }

    private void updateCampainStatus(Campain c) {
        c.setRecordsSent(c.getRecordsCount() - (emailrepo.countUnsentPaginated(c.getId(), EmailFolder.FOLDER_OUTGOING)));
        if(c.getRecordsSent().equals(c.getRecordsCount())) {
        c.setStatus(Campain.STATUS_FINISHED);
        }
        campainRepo.save(c);
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
     * @return the mailgunClient
     */
    public MailGunRestClient getMailgunClient() {
        return mailgunClient;
    }

    /**
     * @param mailgunClient the mailgunClient to set
     */
    public void setMailgunClient(MailGunRestClient mailgunClient) {
        this.mailgunClient = mailgunClient;
    }

    /**
     * @return the campainRepo
     */
    public CampainRepo getCampainRepo() {
        return campainRepo;
    }

    /**
     * @param campainRepo the campainRepo to set
     */
    public void setCampainRepo(CampainRepo campainRepo) {
        this.campainRepo = campainRepo;
    }

    /**
     * @return the emailFolderrepo
     */
    public EmailFolderRepo getEmailFolderrepo() {
        return emailFolderrepo;
    }

    /**
     * @param emailFolderrepo the emailFolderrepo to set
     */
    public void setEmailFolderrepo(EmailFolderRepo emailFolderrepo) {
        this.emailFolderrepo = emailFolderrepo;
    }
}
