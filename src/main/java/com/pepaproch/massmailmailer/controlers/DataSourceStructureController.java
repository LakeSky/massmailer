/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.DataStructure;
import com.pepaproch.massmailmailer.db.entity.DataStructureFields;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<DataStructure> getStructure(@PathVariable("fileId") String fileId) {
        DataStructure ds = new DataStructure();
        
        ds.setFirstRowCnames(Boolean.TRUE);
        List l = new ArrayList();
        for (int i = 1; i < 10; i++) {
            DataStructureFields f = new DataStructureFields(null, "field_" + i, "field_" + i + "_value", i);
            l.add(f);
        }
        ds.setDataStructureFieldsCollection(l);
        ResponseEntity<DataStructure> resp = new ResponseEntity<DataStructure>(ds, HttpStatus.ACCEPTED);
        return resp;
    }

}
