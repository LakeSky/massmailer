/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataStructure;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import com.pepaproch.massmailmailer.poi.impl.XLSProcessor;
import com.pepaproch.massmailmailer.poi.impl.XSSRowToSrcRowMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/datasource/structure")
public class DataSourceStructureController {

    @RequestMapping(value = "/{fileId}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<DataStructure> getStructure(@PathVariable("fileId") String fileId) {

        PoiFlatFileHandler processor = new XLSProcessor(new XSSRowToSrcRowMapper());
        DataStructure ds = processor.getStructure(new File("/tmp/" + fileId));
        RowMapper<RowRecords> rowMapper = processor.process(new File("/tmp/" + fileId));
        Collection previewRows = new ArrayList();
        int i = 0;
        for (RowRecords row : rowMapper) {
            if (i > 0 && i < 6) {
                previewRows.add(new DataSourceRow("preview" + fileId ,row));

            }
            i++;
        }
        ds.setPreviewRows(previewRows);
        ds.setFileName(fileId);

        ResponseEntity< DataStructure> resp = new ResponseEntity<DataStructure>(ds, HttpStatus.ACCEPTED);
        return resp;
    }



}
