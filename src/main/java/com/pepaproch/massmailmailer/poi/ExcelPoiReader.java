/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author pepa
 * @param <T>
 */
public interface ExcelPoiReader<T> {

    /**
     *
     * @param row
     * @return
     */
    T getRow(Row row);

    Workbook getWorkBook(InputStream inp) throws IOException;
    
}
