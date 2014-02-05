/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.entity.DataStructure;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.RowMapper;
import java.io.File;

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
    public  RowMapper process(File f) {
        
        return rowMapper.procces(f);
    }

    @Override
    public DataStructure getStructure(File f) {
        
    }

}
