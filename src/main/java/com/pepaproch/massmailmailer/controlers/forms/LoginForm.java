/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers.forms;

import com.pepaproch.massmailmailer.db.entity.UserInfo;
import com.pepaproch.massmailmailer.security.UserLogin;
import java.util.Set;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author pepa
 */
public class LoginForm {
    private Long  loginId;
    @NotBlank
    private String userName;
    private String password;
    private String newPassword;
    private Set<String> roles;
    private Long userId;


    public LoginForm(UserLogin login) {
       this.loginId = login.getId();
       this.userName = login.getUserName();
       this.roles = login.getRoles();
    }

    public LoginForm() {
 
    }

    public LoginForm(Long userId) {
       this.userId = userId;
    }

    /**
     * @return the loginId
     */
    public Long getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    
}
