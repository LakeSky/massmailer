/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.service;

import com.pepaproch.massmailmailer.db.entity.Users;
import com.pepaproch.massmailmailer.repository.UserRepo;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class UserService {
   @Autowired
   private UserRepo userR; 

    /**
     * @return the userR
     */
    public UserRepo getUserR() {
        return userR;
    }

    /**
     * @param userR the userR to set
     */
    public void setUserR(UserRepo userR) {
        this.userR = userR;
    }
    
    public Iterable<Users> listUsers() {
    return userR.findAll();
    }
}
