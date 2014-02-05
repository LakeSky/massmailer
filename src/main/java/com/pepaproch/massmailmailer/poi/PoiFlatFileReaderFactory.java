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

/**
 *
 * @author pepa
 */
public class PoiFlatFileReaderFactory {

    public PoiFlatFileHandler getReader(PoiTypes p) {

        PoiFlatFileHandler  reader = null;
        switch (p) {
            case CSV:
                reader = new CsvProcessor(new CsvRowToSrcRowMapper());
                break;

            case XLS:
             reader = new XLSProcessor(new HSSRowToSrcRowMapper());
                break;
                        case XLSX:
             reader = new XLSProcessor(new XSSRowToSrcRowMapper());
                break;
            default:
                break;
        }
        return reader;

    }
}
