/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mail.mailgun;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author pepa
 */
public class AttachmenrResource extends AbstractResource {

    private final String fileName;
    private final byte[] attachment;

    @Override
    public String getDescription() {
        return "attachment";
    }

    public AttachmenrResource(byte[] attachment, String fileName) {
        super();
        this.fileName = fileName;
        this.attachment = attachment;
    }

    @Override
    @JsonIgnore
    public InputStream getInputStream() throws IOException {

        return new ByteArrayInputStream(attachment);

    }

    @Override
    public String getFilename() {
        return fileName;
    }

    @Override
    public boolean exists() {
        return Boolean.TRUE;
    }

    @Override
    public URL getURL() throws IOException {
        
       return  new File(fileName).toURI().toURL() ;
    
       
       
    }

    @Override
    public boolean isReadable() {
        return Boolean.TRUE;
    }

    @Override
    public File getFile() throws IOException {
        return null;
    }

    @Override
    public Resource createRelative(String relativePath) {
        return null;
    }

}
