/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pepa
 */
@Controller(value = "IndexController")

@RequestMapping("/index.html")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
    return "redirect:ui/index.html";
    }
    
}
