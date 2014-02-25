/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.util.HashMap;

/**
 *
 * @author pepa
 */
public class DataSourceItem {

    HashMap<String, String> nameValuePair;

    public DataSourceItem(HashMap<String, String> nameValuePair) {
        this.nameValuePair = nameValuePair;
    }

    public DataSourceItem() {
        this.nameValuePair = new HashMap();
    }

    public void add(String name, String value) {
        this.nameValuePair.put(name, value);
    }
   

    /**
     * @param varName
     * @return the varName
     */
    public String getVarValue(String varName) {
        
        return (nameValuePair.containsKey(varName)) ? nameValuePair.get(varName) : null;
    }

}
