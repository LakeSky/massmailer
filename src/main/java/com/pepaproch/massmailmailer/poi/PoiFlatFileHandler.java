/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi;

import com.pepaproch.massmailmailer.db.documents.DataStructureMeta;
import java.io.File;

/**
 *
 * @author pepa
 */
public  interface PoiFlatFileHandler {

    /**
     *

     * @param f
     * @return
     */
    public  RowMapper process(File f);

    public DataStructureMeta getStructure(File f);
}
