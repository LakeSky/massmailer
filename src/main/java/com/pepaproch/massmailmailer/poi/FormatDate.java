/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.poi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pepa
 */
public class FormatDate {
    public static SimpleDateFormat defaultDateFormat = new SimpleDateFormat("MM.dd.yyyy");

    public static String format(Date d) {
        return defaultDateFormat.format(d);
    }

    

}
