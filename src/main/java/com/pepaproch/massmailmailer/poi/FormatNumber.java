/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi;

import java.math.BigDecimal;

/**
 *
 * @author pepa
 */
public class FormatNumber {

    public static String format(BigDecimal d) {
        return d.toString();
    }

    public static String format(Double d) {
        return String.format("%.2f", (double) d);

    }

}
