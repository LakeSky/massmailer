/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

/**
 *
 * @author pepa
 */
public class CampainStatus {
    private String campainName;
    private Long campainId;
    
    private Long recordsCount;
    private Long recordsSent;
    private Long recordsDelivered;
    private Long recordsOpened;
    private Long recordsClicked;
    private Long recordsCapains;

    /**
     * @return the recordsCount
     */
    public Long getRecordsCount() {
        return recordsCount;
    }

    /**
     * @param recordsCount the recordsCount to set
     */
    public void setRecordsCount(Long recordsCount) {
        this.recordsCount = recordsCount;
    }

    /**
     * @return the recordsSent
     */
    public Long getRecordsSent() {
        return recordsSent;
    }

    /**
     * @param recordsSent the recordsSent to set
     */
    public void setRecordsSent(Long recordsSent) {
        this.recordsSent = recordsSent;
    }

    /**
     * @return the recordsDelivered
     */
    public Long getRecordsDelivered() {
        return recordsDelivered;
    }

    /**
     * @param recordsDelivered the recordsDelivered to set
     */
    public void setRecordsDelivered(Long recordsDelivered) {
        this.recordsDelivered = recordsDelivered;
    }

    /**
     * @return the recordsOpened
     */
    public Long getRecordsOpened() {
        return recordsOpened;
    }

    /**
     * @param recordsOpened the recordsOpened to set
     */
    public void setRecordsOpened(Long recordsOpened) {
        this.recordsOpened = recordsOpened;
    }

    /**
     * @return the recordsClicked
     */
    public Long getRecordsClicked() {
        return recordsClicked;
    }

    /**
     * @param recordsClicked the recordsClicked to set
     */
    public void setRecordsClicked(Long recordsClicked) {
        this.recordsClicked = recordsClicked;
    }

    /**
     * @return the recordsCapains
     */
    public Long getRecordsCapains() {
        return recordsCapains;
    }

    /**
     * @param recordsCapains the recordsCapains to set
     */
    public void setRecordsCapains(Long recordsCapains) {
        this.recordsCapains = recordsCapains;
    }

    /**
     * @return the campainName
     */
    public String getCampainName() {
        return campainName;
    }

    /**
     * @param campainName the campainName to set
     */
    public void setCampainName(String campainName) {
        this.campainName = campainName;
    }

    /**
     * @return the campainId
     */
    public Long getCampainId() {
        return campainId;
    }

    /**
     * @param campainId the campainId to set
     */
    public void setCampainId(Long campainId) {
        this.campainId = campainId;
    }
    
}
