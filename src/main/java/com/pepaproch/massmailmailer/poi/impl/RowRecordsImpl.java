/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi.impl;

import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.poi.RowRecords;
import java.util.Collection;
import java.util.Iterator;

public class RowRecordsImpl implements RowRecords<DataSourceField> {

    private final Collection fields;

    public RowRecordsImpl(Collection fields) {
        this.fields = fields;
    }

    @Override
    public Iterator<DataSourceField> iterator() {
        Iterator<DataSourceField> iterator = fields.iterator();
        return iterator;
    }

    @Override
    public Collection<DataSourceField> getFields() {
        return fields;
    }

}
