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
import com.pepaproch.massmailmailer.repository.CampainRepo;
import com.pepaproch.massmailmailer.repository.EmailFolderRepo;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import java.util.Collection;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class CampainSendService {

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

    private void findNotsentCampains() {
        Collection<Campain> findByStatus = campainRepo.findByStatus("SENDING");
        EmailFolder folder = emailFolderrepo.findByEmailFolderId(EmailFolder.FOLDER_OUTBOX);
        for (Campain c : findByStatus) {
            Collection<Email> findUnsentPaginated = emailrepo.findUnsentPaginated(c.getId(), folder.getId(), new PageRequest(page, size));
            for (Email e : findUnsentPaginated) {
                String status = mailgunClient.sendEmail(e);
                System.out.println(status);
                e.setSentDate(new Date());
                e.setEmailFolder(folder);
                e.setStatusDate(e.getSentDate());
                e.setEmailStatus(status);
                emailrepo.save(e);
            }
            updateCampainStatus(c);
        }

    }

    private void updateCampainStatus(Campain c) {
    
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
