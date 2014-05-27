/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.documents;

import com.pepaproch.massmailmailer.poi.DataType;

public class DataStructureTypeVisitor implements CommonVisitor<DataStructure> {

    private final DataType type;
    private Boolean result = Boolean.FALSE;

    public DataStructureTypeVisitor(DataType type) {
        this.type = type;
    }

    @Override
    public void visit(DataStructure d) {
        for (DataStructureMetaField f : d.getDataStructureFields()) {
            if (type.equals(f.getDataType())) {
                result = Boolean.TRUE;
                break;
            }
        }

    }

    public Boolean hasType() {
        return result;
    }

}
