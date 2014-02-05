/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pepa
 */
public class XSSRowToSrcRowMapper implements RowMapper<RowRecords> {
    
    private int currentPoss = 0;
    private int rowCount;
    private Sheet sheet;
    
    @Override
    public RowMapper procces(File f) {
        setUp(f);
        return this;
    }
    
    @Override
    public Iterator<RowRecords> iterator() {
        return new Iterator<RowRecords>() {
            
            @Override
            public boolean hasNext() {
                return (currentPoss <= rowCount);
            }
            
            @Override
            public RowRecords next() {
                Row row = sheet.getRow(currentPoss);
                
                currentPoss++;
                return readRow(row);
                
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    private RowRecordsImpl readRow(Row row) {
        int idx = 0;
        Collection<DataSourceField> rowColl = new ArrayList();
        for (Cell c : row) {
            int type = c.getCellType();
            
            Object value = null;
            switch (type) {
                case HSSFCell.CELL_TYPE_STRING:
                    value = c.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    value = c.getNumericCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    value = c.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    value = c.getStringCellValue();
                    break;
            }
            
            DataSourceField field = new DataSourceField(idx, value);
            rowColl.add(field);
            idx++;
        }
        return new RowRecordsImpl(rowColl);
    }
    
    private void setUp(File f) {
        InputStream inp;
        
        try {
            
            inp = new BufferedInputStream(new FileInputStream(f));
            Workbook wb = new XSSFWorkbook(inp);
            sheet = wb.getSheetAt(0);
            rowCount = sheet.getLastRowNum();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XSSRowToSrcRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex2) {
            
        }
    }
}
