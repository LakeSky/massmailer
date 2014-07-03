/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers.forms;

import java.util.Date;

/**
 *
 * @author pepa
 */
public class SendCampainForm {
    private Long campainId;
    private Date schelduleDate;

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

    /**
     * @return the schelduleDate
     */
    public Date getSchelduleDate() {
        return schelduleDate;
    }

    /**
     * @param schelduleDate the schelduleDate to set
     */
    public void setSchelduleDate(Date schelduleDate) {
        this.schelduleDate = schelduleDate;
    }
}
