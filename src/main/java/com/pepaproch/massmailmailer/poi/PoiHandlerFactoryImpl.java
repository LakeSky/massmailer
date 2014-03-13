/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi;

import com.pepaproch.massmailmailer.poi.impl.CsvProcessor;
import com.pepaproch.massmailmailer.poi.impl.CsvRowToSrcRowMapper;
import com.pepaproch.massmailmailer.poi.impl.HSSRowToSrcRowMapper;
import com.pepaproch.massmailmailer.poi.impl.XLSProcessor;
import com.pepaproch.massmailmailer.poi.impl.XSSRowToSrcRowMapper;
import org.apache.commons.io.FilenameUtils;

public class PoiHandlerFactoryImpl implements PoiHandlerFactory {

    @Override
    public PoiFlatFileHandler getHandler(String fileName) {
        PoiFlatFileHandler handler = null;
        String extension = FilenameUtils.getExtension(fileName);
        PoiTypes type;
        try {
            type = PoiTypes.valueOf(extension.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
        System.out.println(type.getSuffix());

        switch (type) {
            case CSV:
                handler = new CsvProcessor(new CsvRowToSrcRowMapper());
                break;
            case XLS :
                handler = new XLSProcessor(new HSSRowToSrcRowMapper());
                break;
            case XLSX : 
                handler = new XLSProcessor(new XSSRowToSrcRowMapper());
            default:
               
                break;
        }
        return handler;
    }

}
