/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.security.MaillerUserService;
import com.pepaproch.massmailmailer.security.SecurityToken;
import com.pepaproch.massmailmailer.security.TokenUtils;
import com.pepaproch.massmailmailer.security.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/app/login")
public class LoginController {

    @Autowired
    @Qualifier("maillerUserService")
    private MaillerUserService userService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestParam("username") String username, @RequestParam("password") String password) {

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        try{
        Authentication authentication = this.getAuthManager().authenticate(authenticationToken);
           SecurityContextHolder.getContext().setAuthentication(authentication);
           UserLogin userDetails = this.userService.loadUserByUsername(username);
           AppContext.isUserInRole("USER");
            return new ResponseEntity(new SecurityToken(TokenUtils.createToken(userDetails),userDetails.getUserInfo().getId()), HttpStatus.ACCEPTED);
        } catch(AuthenticationException ex ) {
        
            return new ResponseEntity("login failed", HttpStatus.UNAUTHORIZED);
        }
       

    }

    public LoginController() {
    }

    /**
     * @return the authManager
     */
    public AuthenticationManager getAuthManager() {
        return authManager;
    }

    /**
     * @param authManager the authManager to set
     */
    public void setAuthManager(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

}
