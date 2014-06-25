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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;

/**
 *
 * @author pepa
 */
public class WordDocument implements DocumentHolder {

    private final HWPFDocument doc;
    private final PlaceHolderHelper placeHolderRetriver;

    private final String fileName;

    public WordDocument(String inputFilename, PlaceHolderHelper placeHolderRetriver_) throws FileNotFoundException, IOException {
        this.placeHolderRetriver = placeHolderRetriver_;
        this.fileName = inputFilename;
        FileInputStream fis = new FileInputStream(inputFilename);

        doc = new HWPFDocument(fis);
        fis.close();


    }

    public WordDocument(HWPFDocument doc_, PlaceHolderHelper placeHolderRetriver_, String inputFileName) throws FileNotFoundException, IOException {
        this.placeHolderRetriver = placeHolderRetriver_;
        this.fileName = inputFileName;
        doc = doc_;


    }

    @Override
    public String getDocumentText() {
        return doc.getRange().text();
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
    public void procces(TemplateDataItem item, String outputFileName) throws FileNotFoundException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        doc.write(os);
        ByteArrayInputStream bi = new ByteArrayInputStream(os.toByteArray());
        HWPFDocument docCopy = new HWPFDocument(bi);
        DocumentHolder holderCopy = new WordDocument(docCopy, placeHolderRetriver, this.fileName);
        placeHolderRetriver.setPlaceHolders(holderCopy, item);
        holderCopy.write(outputFileName);

    }

    @Override
    public byte[] procces(TemplateDataItem item) throws FileNotFoundException, IOException {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            doc.write(os);
            ByteArrayInputStream bi = new ByteArrayInputStream(os.toByteArray());
            HWPFDocument docCopy = new HWPFDocument(bi);
            DocumentHolder holderCopy = new WordDocument(docCopy, placeHolderRetriver, this.fileName);
            placeHolderRetriver.setPlaceHolders(holderCopy, item);
            return holderCopy.getOutputStream();


    }

    @Override
    public void setVariable(String varName, String varValue) {
       int paraCount = doc.getRange().numParagraphs();
       int currPara = 0;
       while(paraCount>currPara) {
           Paragraph paragraph = doc.getRange().getParagraph(currPara);
           ParagraphProperties cloneProperties = paragraph.cloneProperties();
           paragraph.replaceText(varName, varValue);
           currPara++;
       }

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

    @Override
    public TemplateMeta getTemplateMeta() {
        TemplateMeta meta = new TemplateMeta();
        meta.setPlaceHolders(this.getPlaceHolders());
        meta.setFileName(fileName);
        return meta;
    }


    public byte[] getOutputStream() {
        byte[] result = null;
        try (
             ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            doc.write(os);
            result = os.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(WordDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
