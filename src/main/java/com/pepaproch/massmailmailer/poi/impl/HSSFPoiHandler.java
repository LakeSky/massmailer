/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.poi.ExcelPoiReader;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.SourceRow;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author pepa
 */
public class HSSFPoiHandler implements ExcelPoiReader<SourceRow> {
    
    
    private RowMapper rowMapper;
    
    
    @Override
   public Workbook getWorkBook(InputStream inp) throws IOException {
    return new HSSFWorkbook(inp);
    }

    @Override
    public SourceRow getRow(Row row) {
        return null;
  
    }



 
    
}
