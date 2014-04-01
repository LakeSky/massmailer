/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.poi.DocumentFactory;
import com.pepaproch.massmailmailer.poi.DocumentFactoryImpl;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.PlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.TemplateMeta;
import com.pepaproch.massmailmailer.poi.convert.WordDocument;
import com.pepaproch.massmailmailer.service.UserService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private UserService userService;
    @Autowired
    private ConvertService convertService;
    @Autowired
    private TemlateService templateService;

    @ResponseBody
    @RequestMapping(value = "templatefields/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResponseEntity<TemplateMeta> getTemplateFields(@PathVariable("fileId") String fileName, HttpServletResponse response) {

        PlaceHolderHelper pl = new StringPlaceHolderHelper("###");
        try {
            DocumentFactory documentFactory = new DocumentFactoryImpl();
            DocumentHolder docu = documentFactory.getDocument("/tmp/" + fileName, pl);

            ResponseEntity<TemplateMeta> responseE = new ResponseEntity<TemplateMeta>(docu.getTemplateMeta(), HttpStatus.ACCEPTED);
            return responseE;

        } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @ResponseBody
    @RequestMapping(value = "preview/pdf*", method = {RequestMethod.GET}, produces = "application/pdf")
    public FileSystemResource getTemplatePreviewPdf(@RequestParam(value = "datasourceId", required = false) String datasourceId, @RequestParam(value = "fileId", required = true) String fileName, HttpServletResponse response) throws IOException {
        String previeFile = "/tmp/" + fileName;
        String filedTemplate = "/tmp/";
        if (datasourceId != null) {
            filedTemplate += templateService.createPreview("/tmp/" + fileName, datasourceId);
        }

        convertService.convert(filedTemplate, previeFile + ".pdf", Boolean.FALSE);
        return new FileSystemResource(previeFile + ".pdf");

    }

    @ResponseBody
    @RequestMapping(value = "preview/html*", method = {RequestMethod.GET}, produces = MediaType.TEXT_HTML_VALUE)
    public FileSystemResource getTemplatePreviewHtml(@RequestParam(value = "datasourceId", required = false) String datasourceId, @RequestParam(value = "fileId", required = true) String fileName, HttpServletResponse response) throws IOException {
        String previeFile = "/tmp/" + fileName;
        if (datasourceId != null) {
            templateService.createPreview("/tmp/" + fileName, datasourceId);
        }

        convertService.convert("/tmp/" + previeFile, "/tmp/" + previeFile + ".html", Boolean.FALSE);
        return new FileSystemResource("/tmp/" + previeFile + ".html");

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

    /**
     * @return the convertService
     */
    public ConvertService getConvertService() {
        return convertService;
    }

    /**
     * @param convertService the convertService to set
     */
    public void setConvertService(ConvertService convertService) {
        this.convertService = convertService;
    }

    /**
     * @return the templateService
     */
    public TemlateService getTemplateService() {
        return templateService;
    }

    /**
     * @param templateService the templateService to set
     */
    public void setTemplateService(TemlateService templateService) {
        this.templateService = templateService;
    }

}
