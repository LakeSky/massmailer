/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataStructureMetaField;
import com.pepaproch.massmailmailer.db.documents.DataStructure;
import com.pepaproch.massmailmailer.poi.DataType;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pepa
 */
public class CsvProcessor implements PoiFlatFileHandler {

    RowMapper rowMapper;

    public CsvProcessor(RowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public RowMapper process(File f) {

        return rowMapper.procces(f);
    }

    @Override
    public DataStructure getStructure(RowMapper<RowRecords> rows) {

        ArrayList<DataStructureMetaField> fields = null;
        Iterator<RowRecords> it = rows.iterator();
        //get fields names
        if (it.hasNext()) {
            fields = new ArrayList<DataStructureMetaField>();
            RowRecords<DataSourceField> row = it.next();
            for (DataSourceField field : row) {
                DataStructureMetaField dataStructureField = new DataStructureMetaField(field.getIndex(), field.getValue().toString(), DataType.TEXT);
                fields.add(dataStructureField);

            }
        }

        int recCount = 0;
        List<DataSourceRow> previewRows = new ArrayList();
        while (it.hasNext() && recCount < 6) {
            previewRows.add(new DataSourceRow("preview" + recCount, it.next()));
            recCount++;
        }

        DataStructure ds = new DataStructure(fields);
        ds.setPreviewRows(previewRows);
        return ds;

    }

}
