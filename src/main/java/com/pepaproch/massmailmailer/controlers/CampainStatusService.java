/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.EmailStatus;
import com.pepaproch.massmailmailer.repository.EmailStatusRepo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class CampainStatusService {

    @Autowired
    private EmailStatusRepo emailStatusRepo;

    public CampainStatus getAllCampainsStatus() {
        CampainStatus status = new CampainStatus();
        status.setRecordsSent(emailStatusRepo.getSentOverAll());
        status.setRecordsDelivered(emailStatusRepo.getByStatus(EmailStatus.DELIVERED));
        status.setRecordsOpened(emailStatusRepo.getByStatus(EmailStatus.OPENED));
        status.setRecordsCapains(emailStatusRepo.getSentCampainsOverAll());
        return status;

    }

    public CampainStatus getCampainStatus(Long campainId) {
        CampainStatus status = new CampainStatus();
        status.setRecordsSent(emailStatusRepo.getSentForCampain(campainId));
        return status;

    }

    public CampainStatus getCampainsStatus(List<Long> campainIds) {
        CampainStatus status = new CampainStatus();
        return status;

    }

    public CampainStatus getCampainsStatusByPeriod(Date styartDate, Date endDate) {
        CampainStatus status = new CampainStatus();
        return status;

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
