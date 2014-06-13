/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.entity;

/**
 *
 * @author pepa
 */
public   class AbstracSpec {

    public static final String EQUAL_COMPARE = "eq";
    public static final String LIKE_COMPARE = "like";
    public static final String LT_COMPARE = "lt";
        public static final String ALL = "all";
    
    /**
     *
     * @param string
     * @return
     */
    public static String getLikeString(String string) {
        return new StringBuilder("%").append(string.toLowerCase()).append("%").toString();

    }
}
