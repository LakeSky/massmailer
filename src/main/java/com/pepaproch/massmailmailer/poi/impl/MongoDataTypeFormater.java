/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi.impl;

/**
 *
 * @author pepa
 */
public  class MongoDataTypeFormater {
    
    private MongoDataTypeFormater(){
        
    }
    
    public static String format(String s) {
    
    return s;
    }
    
    public static String format(Double d) {
    return d.toString();
    }
    
    public static String format(Integer d) {
    return d.toString();
    }
}
