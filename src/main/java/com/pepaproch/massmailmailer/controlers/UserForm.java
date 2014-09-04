/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.UserInfo;
import java.util.Set;

/**
 *
 * @author pepa
 */
public class UserForm extends UserInfo {
    private String userName;
    private String plainPassWord;
    private Set<String> userRoles;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the plainPassWord
     */
    public String getPlainPassWord() {
        return plainPassWord;
    }

    /**
     * @param plainPassWord the plainPassWord to set
     */
    public void setPlainPassWord(String plainPassWord) {
        this.plainPassWord = plainPassWord;
    }

    /**
     * @return the userRoles
     */
    public Set<String> getUserRoles() {
        return userRoles;
    }

    /**
     * @param userRoles the userRoles to set
     */
    public void setUserRoles(Set<String> userRoles) {
        this.userRoles = userRoles;
    }
}
