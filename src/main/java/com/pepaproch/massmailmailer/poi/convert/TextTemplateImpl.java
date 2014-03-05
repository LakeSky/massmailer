/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi.convert;

import com.pepaproch.massmailmailer.db.TextDocumentHolder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepa
 */
public class TextTemplateImpl implements TextTemplate{
  private final TextDocumentHolder doc;
  private final TemplateDataItem itemVars;


    public TextTemplateImpl(TextDocumentHolder doc, TemplateDataItem itemVars) {
        this.doc = doc;
        this.itemVars = itemVars;
    }
  

    @Override
    public String procces() {
      
       return   doc.procces(itemVars);
         

    }



 
 
    
}
