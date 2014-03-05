/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPlaceHolderHelper implements PlaceHolderHelper {

    private final Pattern pattern;

    public StringPlaceHolderHelper(String MARKUP) {
        pattern = Pattern.compile(MARKUP+".*"+MARKUP);
    }

    @Override
    public Collection<String> getPlaceHolders(TemplateHolder docHolder) {
        List<String> tr = new ArrayList();
    
        Matcher m = pattern.matcher(docHolder.getDocumentText());
        while (m.find()) {
            if (!tr.contains(m.group())) {
                tr.add(m.group());
            }
        }

        return tr ;

    }

    /**
     *
     * @param dcoHolder
     * @param item
     *
     */
    @Override
    public void setPlaceHolders(TemplateHolder dcoHolder, TemplateDataItem item) {
        for (String varName : item.nameValuePair.keySet()) {
            dcoHolder.setVariable(varName, item.getVarValue(varName));
        }

    }

}
