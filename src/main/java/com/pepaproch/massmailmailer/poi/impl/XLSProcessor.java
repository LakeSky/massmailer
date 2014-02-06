/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.db.documents.DataStructureMetaField;
import com.pepaproch.massmailmailer.db.documents.DataStructureMeta;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author pepa
 */
public class XLSProcessor implements PoiFlatFileHandler {

    RowMapper rowMapper;

    public XLSProcessor(RowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public RowMapper process(File f) {

        return rowMapper.procces(f);
    }

    @Override
    public DataStructureMeta getStructure(File file) {
        RowMapper<RowRecords> rows = process(file);
        ArrayList<DataStructureMetaField> fields = null;
        //get fields names
        Iterator it = rows.iterator();
        if (it.hasNext()) {
            fields =new ArrayList<DataStructureMetaField>();
            RowRecords<DataSourceField> row = (RowRecords<DataSourceField>) it.next();
            for (DataSourceField field : row) {
                DataStructureMetaField dataStructureField = new DataStructureMetaField(field.getIndex(), field.getValue().toString());
                fields.add(dataStructureField);

            }
        }

        return new DataStructureMeta(fields);
    }

}
