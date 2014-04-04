/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author pepa
 */
public interface DocumentHolder extends TemplateHolder{


    public void procces(TemplateDataItem item, String outputFileName) throws FileNotFoundException, IOException;
    public void write(String outputFileName) throws FileNotFoundException, IOException;
    public TemplateMeta getTemplateMeta();
    public byte[] procces(TemplateDataItem item) throws FileNotFoundException, IOException;
   public byte[] getOutputStream();



}
