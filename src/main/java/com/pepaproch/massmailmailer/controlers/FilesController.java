/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;


import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/files")
public class FilesController {





    @ResponseBody
    @RequestMapping(produces = MediaTypes.MEDIA_TYPEJSONUTF8VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile mfile, HttpServletResponse response) {
        String fileName = RequestContextHolder.currentRequestAttributes().getSessionId() + "_" + mfile.getOriginalFilename();
        String destination = "/tmp/" + fileName;

        File file = new File(destination);
        try {
            mfile.transferTo(file);
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(FilesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResponseEntity<String> responseE = new ResponseEntity<>(fileName, HttpStatus.CREATED);
        return responseE;

    }

    @ResponseBody
    @RequestMapping(value = "/datasource", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity uploadDataSourceFile(@RequestParam("file") MultipartFile mfile, HttpServletResponse response) {
        String fileName = RequestContextHolder.currentRequestAttributes().getSessionId() + "_" + mfile.getOriginalFilename();
        String destination = "/tmp/" + fileName;
        File file = new File(destination);
        try {
            mfile.transferTo(file);
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(FilesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResponseEntity<String> responseE = new ResponseEntity<>(fileName, HttpStatus.CREATED);
        return responseE;

    }

    @RequestMapping(value = "/{fileId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.DELETE})
    @ResponseBody
    public ResponseEntity deleteFile(@PathVariable("fileId") Integer id, BindingResult result) {

        return null;
    }

    public FilesController() {
    }



}
