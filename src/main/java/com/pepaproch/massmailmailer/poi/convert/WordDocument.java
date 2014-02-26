/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

/**
 *
 * @author pepa
 */
public class WordDocument implements DocumentHolder {

    private final HWPFDocument doc;
    private final PlaceHolderHelper placeHolderRetriver;
    private final Range range;

    public WordDocument(String inputFilename, PlaceHolderHelper placeHolderRetriver_) throws FileNotFoundException, IOException {
        this.placeHolderRetriver = placeHolderRetriver_;
        FileInputStream fis = new FileInputStream(inputFilename);

        doc = new HWPFDocument(fis);
        fis.close();
        range = doc.getRange();

    }

    public WordDocument(HWPFDocument doc_, PlaceHolderHelper placeHolderRetriver_) throws FileNotFoundException, IOException {
        this.placeHolderRetriver = placeHolderRetriver_;

        doc = doc_;
        range = doc.getRange();

    }

    @Override
    public String getDocumentText() {
        return range.text();
    }

    @Override
    public Collection<String> getPlaceHolders() {
        return placeHolderRetriver.getPlaceHolders(this);
    }

    /**
     *
     * @param item
     * @param outputFileName
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public void procces(DataSourceItem item, String outputFileName) throws FileNotFoundException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        doc.write(os);
        ByteArrayInputStream bi = new ByteArrayInputStream(os.toByteArray());
        HWPFDocument docCopy = new HWPFDocument(bi);
        DocumentHolder holderCopy = new WordDocument(docCopy, placeHolderRetriver);
        placeHolderRetriver.setPlaceHolders(holderCopy, item);
        holderCopy.write(outputFileName);

    }

    @Override
    public void setVariable(String varName, String varValue) {
        range.replaceText(varName, varValue);
    }

    @Override
    public void write(String outputFileName) throws FileNotFoundException, IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputFileName);
            doc.write(fos);

        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

}
