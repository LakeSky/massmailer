/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db;

import com.pepaproch.massmailmailer.poi.convert.TemplateDataItem;
import com.pepaproch.massmailmailer.poi.convert.TemplateHolder;
import java.util.Collection;

/**
 *
 * @author pepa
 */
public interface TextDocumentHolder extends TemplateHolder{
    @Override
    public String getDocumentText();
    @Override
    public Collection<String> getPlaceHolders();
    public String procces(TemplateDataItem item);

}
