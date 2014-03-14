/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import java.nio.charset.Charset;
import org.springframework.http.MediaType;

/**
 *
 * @author pepa
 */
public class MediaTypes {
    public static final MediaType  MEDIA_TYPEJSON  = new MediaType( "application", "json", Charset
                                                      .forName( "UTF-8" ) );
    

    public static final String  MEDIA_TYPEJSONUTF8VALUE  = "application/json;charset=utf-8" ;

}
