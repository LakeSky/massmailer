/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.transaction.Transactional;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class ConvertService {

    private final Map<String, HashSet<String>> suportMap = new HashMap<>();

    OfficeManager officeManager;



    public ConvertService() {

    }

    public void convert(String inFileName, String outFileName, Boolean removeInput) {

        File inputFile = new File(inFileName);
        try {

            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            converter.convert(inputFile, new File(outFileName));

        } finally {
            if (removeInput) {
                boolean delete = inputFile.delete();
            }

        }

    }

    public byte[] convert(byte[] file, String inFileType, String outFileType, Boolean removeInput) throws IOException {
        FileOutputStream fos = new FileOutputStream("/tmp/tmpname." + inFileType);
        fos.write(file);
        
        File inputFile = new File("/tmp/tmpname." + inFileType);
        File output = new File("/tmp/file.pdf");
        try {

            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

            converter.convert(inputFile, output);
            byte[] b = new byte[(int) output.length()];
            try (
                    FileInputStream fis = new FileInputStream(output);) {

                fis.read(b);
            } catch (IOException e) {

            }

            return b;

        } finally {
            if (removeInput) {
                inputFile.delete();
                output.delete();

            }

        }

    }

    public void startManager() {
        if (officeManager == null) {
            officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
        } else {
            officeManager.stop();
        }
        System.out.println("STARTING OFFICE MANAGER");
        officeManager.start();

    }

    public void stopManager() {
        System.out.println("STOPPING OFFICE MANAGER");
        officeManager.stop();
    }



}
