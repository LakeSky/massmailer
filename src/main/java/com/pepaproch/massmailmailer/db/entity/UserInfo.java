/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author pepa
 */
@Entity
public class UserInfo implements Serializable {
    @Id
    private Long id;
    private String firstName;
    private String lastName;

}