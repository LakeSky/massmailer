/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.convert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;


/**
 *
 * @author pepa
 */
public class DocToPdfTest {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        PlaceHolderHelper pl = new StringPlaceHolderHelper("####");

        DocumentHolder docu = new WordDocument("/home/pepa/test.doc", pl);
        List<TemplateDataItem> items = new ArrayList();
        for (int i = 0; i < 10; i++) {
            TemplateDataItem dat = new TemplateDataItem();
            dat.add("####NAME####", "PEPA " + i);
            dat.add("####TEL####", "00420 444 567 89" + i);
            items.add(dat);
        }
            OfficeManager officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
        try {
        // TODO code application logic here
     
        officeManager.start();
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        int c = 0;
        for (TemplateDataItem dat : items) {

            String outputFilename = "/home/pepa/CONVTEST/TMP/OUT" + c + ".html";

            DocumentTemplate template = new TemplateImpl(docu, dat);
            template.procces(outputFilename);

            converter.convert(new File(outputFilename), new File("/home/pepa/CONVTEST/PDFOUT/PDF"+c+".html"));
            c++;
        }
        }
        finally {
           officeManager.stop();
        }

     
    }

}
