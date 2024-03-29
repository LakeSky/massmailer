/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataStructure;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private DataSourceRowService dataSourceRowService;

    @RequestMapping(value = "/{fileId}/{timeStamp}", produces = MediaTypes.MEDIA_TYPEJSONUTF8VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getStructure(@PathVariable("fileId") String fileId, @PathVariable("timeStamp") String timeStamp) {

        DataStructure ds = null;
        try {
            ds = getDataSourceRowService().getDataStructureFrimFile(new File("/tmp/" + fileId));
        } catch (IllegalArgumentException e) {
            ResponseEntity<String> error = new ResponseEntity<>("Soubor nelze použít", HttpStatus.BAD_REQUEST);
            return error;
        }

        ResponseEntity< DataStructure> resp = new ResponseEntity<>(ds, HttpStatus.ACCEPTED);
        return resp;
    }

    /**
     * @return the dataSourceRowService
     */
    public DataSourceRowService getDataSourceRowService() {
        return dataSourceRowService;
    }

    /**
     * @param dataSourceRowService the dataSourceRowService to set
     */
    public void setDataSourceRowService(DataSourceRowService dataSourceRowService) {
        this.dataSourceRowService = dataSourceRowService;
    }

}
