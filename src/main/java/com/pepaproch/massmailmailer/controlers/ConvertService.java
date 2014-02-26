/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.poi.convert.TemplateDataItem;
import com.pepaproch.massmailmailer.poi.convert.Template;
import com.pepaproch.massmailmailer.poi.convert.TemplateImpl;
import java.io.File;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeConnectionProtocol;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class ConvertService {

    public ConvertService() {
    }

    public void convert(String inFileName, String outFileName, Boolean removeInput) {
        OfficeManager officeManager = new DefaultOfficeManagerConfiguration()
      .setConnectionProtocol(OfficeConnectionProtocol.PIPE)
      .setPipeNames("office1", "office2")
      .setTaskExecutionTimeout(30000L).buildOfficeManager();
        File inputFile = new File(inFileName);
        try {

            officeManager.start();
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            converter.convert(inputFile, new File(outFileName));

        } finally {
            if (removeInput) {
                boolean delete = inputFile.delete();
            }
            officeManager.stop();
        }

    }
}
