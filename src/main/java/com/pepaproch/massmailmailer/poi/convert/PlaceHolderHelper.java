/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi.convert;

import java.util.Collection;

/**
 *
 * @author pepa
 */
public interface PlaceHolderHelper {
    public Collection<String> getPlaceHolders(TemplateHolder  dcoHolder) ;
   
    public void setPlaceHolders(TemplateHolder dcoHolder , TemplateDataItem item);
}
