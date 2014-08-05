/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;




import com.pepaproch.massmailmailer.db.entity.UserInfo;
import com.pepaproch.massmailmailer.repository.UserInfoDao;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pepa
 */
@Controller
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserInfoDao userDao;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> listUserInfo() {
        return (List) getUserDao().findAll();
    }

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public UserInfo showUser(@PathVariable("userId") Long userId) {
        UserInfo user = getUserDao().findOne(userId);
        return user;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveUserInfo(@Valid @RequestBody UserInfo user, BindingResult result) {

        return saveUser(user,result);

    }

    @RequestMapping(value = "/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE,method = { RequestMethod.PUT,RequestMethod.POST} )
    @ResponseBody
    public ResponseEntity updateeUserInfo(@Valid @RequestBody UserInfo user, BindingResult result) {

        return saveUser(user,result);

    }
    
    private ResponseEntity saveUser(UserInfo user,BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<FieldError> fieldErrors = result.getFieldErrors();
            ResponseEntity<List<FieldError>> errorResponse = new ResponseEntity<List<FieldError>>(fieldErrors, HttpStatus.UNPROCESSABLE_ENTITY);
            return errorResponse;
        } else {
            UserInfo savedUser = getUserDao().save(user);
            ResponseEntity<UserInfo> responseEntity = new ResponseEntity(savedUser, HttpStatus.CREATED);
            return responseEntity;
        }
    }

    public UserInfoController() {
    }

    /**
     * @return the userDao
     */
    public UserInfoDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserInfoDao userDao) {
        this.userDao = userDao;
    }

 

}
