/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author pepa
 */
public class PoiHandlerFactoryImplTest {

    private final PoiHandlerFactory factory;

    public PoiHandlerFactoryImplTest() {
        factory = new PoiHandlerFactoryImpl();
    }

    /**
     * Test of getHandler method, of class PoiHandlerFactoryImpl.
     */
    @Test
    public void testGetHandler() {
        PoiFlatFileHandler dochandler = factory.getHandler("test.doc");
        assertNull(dochandler);
        PoiFlatFileHandler pdfhandler = factory.getHandler("test.pdf");
        assertNull(dochandler);
        PoiFlatFileHandler docxhandler = factory.getHandler("test.docx");

        PoiFlatFileHandler xlsxhandler = factory.getHandler("test.xlsx");
        assertNotNull(xlsxhandler);
        PoiFlatFileHandler xlshandler = factory.getHandler("test.xls");
        assertNotNull(xlshandler);
        PoiFlatFileHandler csvhandler = factory.getHandler("test.csv");
        assertNotNull(csvhandler);
    }

}
