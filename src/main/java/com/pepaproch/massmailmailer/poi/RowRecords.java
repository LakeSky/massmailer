/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.poi;

import java.util.Collection;

/**
 *
 * @author pepa
 * @param <T>
 */
public interface RowRecords<T> extends Iterable<T> {
    public Collection getFields();
}
