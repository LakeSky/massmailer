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

    XLS("xls",false),
    XLSX("xlsx",false),
    CSV("csv",false),
    HTML("html",true),
    DOC("doc",true),
    DOCX("docx",true),
    ODS("ods",false);

    private final String suffix;
    private final Boolean isTemplate;

    
    public  String getSuffix() {
    return suffix;
    }
      
    PoiTypes(String suffix,Boolean isTemplate) {
        this.suffix = suffix;
        this.isTemplate = isTemplate;
    }
    
    public Boolean isTemplate() {
    return this.isTemplate;
    }
}
