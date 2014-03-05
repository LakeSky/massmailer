/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db;

import com.pepaproch.massmailmailer.poi.convert.PlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.TemplateDataItem;
import com.pepaproch.massmailmailer.poi.convert.TemplateHolder;
import java.util.Collection;

/**
 *
 * @author pepa
 */
public class HtmlDocument implements TextDocumentHolder{
    private final PlaceHolderHelper placeHolderRetriver;
    private String html;
            

    public HtmlDocument(PlaceHolderHelper placeHolderRetriver, String html) {
        this.placeHolderRetriver = placeHolderRetriver;
        this.html = html;
    }
    
    
    @Override
    public String getDocumentText() {
       return html;
    }

    @Override
    public Collection<String> getPlaceHolders() {
          return placeHolderRetriver.getPlaceHolders(this);
    }

    @Override
    public String procces(TemplateDataItem item) {
        TemplateHolder copy = new HtmlDocument(placeHolderRetriver, html);      
    placeHolderRetriver.setPlaceHolders(copy, item);
    return copy.getDocumentText();
    }

    @Override
    public void setVariable(String varName, String varValue) {
        this.html = html.replaceAll(varName, varValue);
    }

 
    
}
