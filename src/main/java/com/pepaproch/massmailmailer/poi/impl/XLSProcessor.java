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
public class XLSProcessor implements PoiFlatFileHandler {

    private final RowMapper rowMapper;

    public XLSProcessor(RowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public RowMapper process(File f) {

        return rowMapper.procces(f);
    }

    @Override
    public DataStructure getStructure(RowMapper<RowRecords> rows) {

        ArrayList<DataStructureMetaField> fields = null;
        //get fields names
        Iterator<RowRecords> it = rows.iterator();
        if (it.hasNext()) {
            fields = new ArrayList();
            RowRecords<DataSourceField> rowColumnNames = (RowRecords<DataSourceField>) it.next();
            RowRecords<DataSourceField> rowData = null;
            if (it.hasNext()) {
                rowData = (RowRecords<DataSourceField>) it.next();

            } else {
                rowData = rowColumnNames;
            }

            if (rowColumnNames.getFields().size() < rowData.getFields().size()) {
                //throw exception not valid source
            }

            for (int columnIdx = 0; columnIdx < rowColumnNames.getFields().size(); columnIdx++) {
                String name = ((DataSourceField) rowColumnNames.getFields().toArray()[columnIdx]).getValue().toString();
                DataType dataType = ((DataSourceField) rowData.getFields().toArray()[columnIdx]).getDataType();
                DataStructureMetaField dataStructureField = new DataStructureMetaField(columnIdx, name, dataType);
                fields.add(dataStructureField);

            }

        }
        int recCount = 0;
        List<DataSourceRow> previewRows = new ArrayList();
        it = rows.iterator();
        while(it.hasNext() && recCount<6) {
            RowRecords next = it.next();
            //skip header
            if(recCount>0) {
             previewRows.add(new DataSourceRow("preview" + recCount  ,next));
            }
        
        recCount++;
        }
        
        DataStructure ds = new DataStructure(fields);
        ds.setPreviewRows(previewRows);

        return ds;
    }

}
