/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.security;

/**
 *
 * @author pepa
 */
public class SecurityToken {
    private String token;
    private final Long userId;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    public SecurityToken(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }
}
