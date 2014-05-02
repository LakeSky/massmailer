/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.controlers.forms.ImageFolder;
import com.pepaproch.massmailmailer.service.UserService;
import java.io.File;
import java.io.IOException;
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
@RequestMapping("/images")
public class ImgController {

    private final String imageDirectory = "/home/pepa/NetBeansProjects/MassMailMailer/resources/";
    private final String serverPath = "resources";
    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<ImageFolder> listFolders() {
        return (List) userService.listUsers();
    }
    
    @ResponseBody
     @RequestMapping(value="/{folderName}" ,produces = MediaType.APPLICATION_JSON_VALUE, method = { RequestMethod.PUT,RequestMethod.POST} )
    public ResponseEntity uploadFile(@PathVariable(value = "folderName" ) String folderName,@RequestParam("file") MultipartFile mfile, HttpServletResponse response) {
        String fileName = RequestContextHolder.currentRequestAttributes().getSessionId() + "_" +  mfile.getOriginalFilename();
        String destination = "/tmp/" + fileName ;
        File file = new File(destination);
        try {
            mfile.transferTo(file);
        } catch (IOException ex) {
            Logger.getLogger(ImgController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ImgController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResponseEntity<String> responseE = new ResponseEntity<String>(fileName,HttpStatus.CREATED);
        return responseE;

    }
    
    

    @RequestMapping(value = "/{fileId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.DELETE})
    @ResponseBody
    public ResponseEntity deleteFile(@PathVariable("fileId") Integer id, BindingResult result) {

        return null;
    }

    public ImgController() {
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
