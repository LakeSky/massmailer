/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.db.documents;

import com.pepaproch.massmailmailer.poi.DataType;

/**
 *
 * @author pepa
 * @param <T>
 */
public class DataSourceField<T> {

    private Integer index;
    private T value;
    private DataType dataType;

    public DataSourceField() {

    }

    public DataSourceField(Integer index, T value, DataType dataType) {
        this.index = index;
        this.value = value;
        this.dataType = dataType;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    public String stringValue() {
        return getDataType().format(value);

    }

    /**
     * @return the dataType
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

}
