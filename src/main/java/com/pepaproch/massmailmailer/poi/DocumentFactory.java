/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi;

import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.PlaceHolderHelper;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author pepa
 */
public interface DocumentFactory {
    public DocumentHolder getDocument(String fileName,PlaceHolderHelper placeholderHelper) throws FileNotFoundException ,IOException;
}
