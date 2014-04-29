/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author pepa
 */
public class SimpleDocument implements SimpleFileHolder {

    private  byte[] byteArray;

    public SimpleDocument(File f) {
        if (f.exists()) {
            try {
                byteArray = FileUtils.readFileToByteArray(f);
            } catch (IOException e) {
           
            }
        } else {
        byteArray = null;
        }
    }

    @Override
    public byte[] getOutputStream() {
        return byteArray;
    }

}
