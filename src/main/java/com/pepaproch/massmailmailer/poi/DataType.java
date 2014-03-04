/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author pepa
 */
public enum DataType {

    DATE("DATE") {
                @Override
                public String format(Object o) {
                    return FormatDate.format((Date) o);
                }
            },
    TEXT("TEXT") {
                @Override
                public String format(Object o) {
                    return FormatString.format((String) o);
                }
            },
    EMAIL("EMAIL") {
                @Override
                public String format(Object o) {
                    return FormatString.format((String) o);
                }
            },
    NUMBER("NUMBER") {
                @Override
                public String format(Object o) {
                    if (o instanceof Double) {
                        return FormatNumber.format((Double) o);
                    } else if (o instanceof BigDecimal) {
                        return FormatNumber.format((BigDecimal) o);
                    } else {
                        return o.toString();
                    }
                }
            };

    private final String type;

    public abstract String format(Object o);

    DataType(String type) {
        this.type = type;
    }
}
