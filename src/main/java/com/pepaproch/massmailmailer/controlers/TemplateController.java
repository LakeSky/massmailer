/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.Files;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.PlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.WordDocument;
import com.pepaproch.massmailmailer.service.UserService;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
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
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "templatefields/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity getTemplateFields(@RequestParam("fileId") String fileName, HttpServletResponse response) {

        PlaceHolderHelper pl = new StringPlaceHolderHelper("####");
        try {
            DocumentHolder docu = new WordDocument("/tmp/" + fileName, pl);
            ResponseEntity<Collection<String>> responseE = new ResponseEntity<Collection<String>>(docu.getPlaceHolders(), HttpStatus.ACCEPTED);
            return responseE;

        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @RequestMapping(value = "/{fileId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.DELETE})
    @ResponseBody
    public ResponseEntity deleteFile(@PathVariable("fileId") Integer id, BindingResult result) {

        return null;
    }

    public TemplateController() {
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
