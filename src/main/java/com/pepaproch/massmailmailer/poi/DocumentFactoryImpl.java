/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi;

import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.PlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.WordDocument;
import com.pepaproch.massmailmailer.poi.convert.WordXDocument;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;


public class DocumentFactoryImpl implements DocumentFactory {

    @Override
    public DocumentHolder getDocument(String fileName,PlaceHolderHelper placeHolderHelper) throws FileNotFoundException, IOException{
        DocumentHolder  document = null;
        String extension = FilenameUtils.getExtension(fileName);
        PoiTypes type;
        try {
            type = PoiTypes.valueOf(extension.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
        System.out.println(type.getSuffix());

        switch (type) {
            case DOC:
              document = new WordDocument(fileName, placeHolderHelper);
                break;
            case DOCX :
            document = new WordXDocument(fileName, placeHolderHelper);
                break;

            default:
               
                break;
        }
        return document;
    }
    
}
