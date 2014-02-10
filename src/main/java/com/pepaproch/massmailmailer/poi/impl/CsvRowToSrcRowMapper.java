/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.poi.DataType;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author pepa
 */
public class CsvRowToSrcRowMapper implements RowMapper<RowRecords> {

  
    Iterator<CSVRecord> iterator;



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
                return iterator.hasNext();
            }

            @Override
            public RowRecords next() {
                CSVRecord record = iterator.next();
                Iterator<String> iterator = record.iterator();
                int idx = 0;
                Collection<DataSourceField> rowColl = new ArrayList();
                while (iterator.hasNext()) {
                    DataSourceField<String> field = new DataSourceField<String>(idx, iterator.next(),DataType.TEXT);
                    rowColl.add(field);
                    idx++;

                }
                return new RowRecordsImpl(rowColl);

            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    private void setUp(File f) {
        InputStream inp;

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            iterator = CSVFormat.EXCEL.parse(br).iterator();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvRowToSrcRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CsvRowToSrcRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
