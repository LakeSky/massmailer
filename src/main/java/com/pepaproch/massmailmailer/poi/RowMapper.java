/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi;

import java.io.File;

/**
 *
 * @author pepa
 * @param <T>
 */
public  interface RowMapper<T> extends Iterable<T> {
    
    public RowMapper procces(File f);

    
}
