/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author pepa
 */
public interface DocumentHolder {

    public String getDocumentText();
    public Collection<String> getPlaceHolders();
    public void procces(DataSourceItem item, String outputFileName) throws FileNotFoundException, IOException;
    public void setVariable(String varName, String varValue);
    public void write(String outputFileName) throws FileNotFoundException, IOException;



}
