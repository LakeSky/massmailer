/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepa
 */
public class TemplateImpl implements DocumentTemplate {

    private final DocumentHolder doc;
    private final TemplateDataItem itemVars;

    public TemplateImpl(DocumentHolder doc, TemplateDataItem itemVars) {
        this.doc = doc;
        this.itemVars = itemVars;
    }

    @Override
    public void procces(String outputFileName) {
        try {
            doc.procces(itemVars, outputFileName);

        } catch (IOException ex) {
            Logger.getLogger(TemplateImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public byte[] procces() {
        byte[] result = null;
        try {
            result =   doc.procces(itemVars);
        } catch (IOException ex) {
            Logger.getLogger(TemplateImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;

    }

}
