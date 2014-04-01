/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi;

/**
 *
 * @author pepa
 */
public enum PoiTypes {

    XLS("xls"),
    XLSX("xlsx"),
    CSV("csv"),
    HTML("html"),
    DOC("doc"),
    DOCX("docx"),
    ODS("ods");

    private final String suffix;
    
    public  String getSuffix() {
    return suffix;
    }
      
    PoiTypes(String suffix) {
        this.suffix = suffix;
    }
}
