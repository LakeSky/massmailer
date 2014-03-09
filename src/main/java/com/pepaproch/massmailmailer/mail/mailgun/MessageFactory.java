/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.mail.mailgun;

import com.pepaproch.massmailmailer.db.entity.Email;

/**
 *
 * @author pepa
 * @param <T>
 */
public interface MessageFactory<T> {
    public  T getMessage(Email email);
}
