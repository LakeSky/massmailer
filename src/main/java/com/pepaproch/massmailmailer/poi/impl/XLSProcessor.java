/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.entity.DataStructure;
import com.pepaproch.massmailmailer.db.entity.DataStructureFields;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.RowMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public DataStructure getStructure(File f) {
        DataStructure ds = new DataStructure();

        ds.setFirstRowCnames(Boolean.TRUE);
        List l = new ArrayList();
        for (int i = 0; i < 10; i++) {
            DataStructureFields fields = new DataStructureFields(null, "field_" + i, "field_" + i + "_value", i);
            l.add(f);
        }
        ds.setDataStructureFieldsCollection(l);
        ResponseEntity<DataStructure> resp = new ResponseEntity<DataStructure>(ds, HttpStatus.ACCEPTED);
        return ds;
    }

}
