/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.EmailStatus;
import com.pepaproch.massmailmailer.repository.CampainRepo;
import com.pepaproch.massmailmailer.repository.EmailStatusRepo;
import java.util.ArrayList;
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
    
    @Autowired 
     private CampainRepo campainRepo;

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
        status.setCampainId(campainId);
        status.setCampainName(getCampainRepo().findOne(campainId).getCampainName());
        status.setRecordsSent(emailStatusRepo.getSentForCampain(campainId));
        status.setRecordsDelivered(emailStatusRepo.getByStatusAndCampainId(EmailStatus.DELIVERED, campainId));
        status.setRecordsOpened(emailStatusRepo.getByStatusAndCampainId(EmailStatus.OPENED, campainId));
        status.setRecordsClicked(emailStatusRepo.getByStatusAndCampainId(EmailStatus.CLICKED, campainId));
        return status;

    }

    public CampainStatus getCampainsStatus(List<Long> campainIds) {
        CampainStatus status = new CampainStatus();
        status.setCampainId(0L);
        status.setCampainName("*");
        status.setRecordsSent(emailStatusRepo.getByStatusAndCampainIds(EmailStatus.ACCEPTED, campainIds));
        status.setRecordsDelivered(emailStatusRepo.getByStatusAndCampainIds(EmailStatus.DELIVERED, campainIds));
        status.setRecordsOpened(emailStatusRepo.getByStatusAndCampainIds(EmailStatus.OPENED, campainIds));
        status.setRecordsClicked(emailStatusRepo.getByStatusAndCampainIds(EmailStatus.CLICKED, campainIds));
        return status;

    }

    public CampainStatus getCampainsStatusByPeriod(Date startDate, Date endDate) {
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

    List<CampainStatus> getCampainsStatus(List<Long> campainIds, Boolean separate) {
        List<CampainStatus> result = new ArrayList<>();
        if (separate) {
            for (Long id : campainIds) {

                result.add(getCampainStatus(id));
            }
        } else {
            result.add(getCampainsStatus(campainIds));
        }

        return result;
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
}
