/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.entity.UserInfo;
import com.pepaproch.massmailmailer.repository.UserInfoDao;
import com.pepaproch.massmailmailer.security.UserLoginDao;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private UserLoginDao userLogindao;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> showDataSourceData(
            @RequestParam(value = "page", required = true) String page,
            @RequestParam(value = "limit", required = false) String limit,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "sortDir", required = false) String sortDirection,
            @RequestParam(value = "searchString", required = false) String searchString
    ) {

        Sort sortable = new Sort(Sort.Direction.DESC, sort);
        Pageable pageSpecification = new PageRequest(new Integer(page), new Integer(limit), sortable);
        List<UserInfo> findByDataSourceIdPaginated = getUserInfoService().searchAll(pageSpecification, searchString);
        return findByDataSourceIdPaginated;
    }

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public UserInfo showUser(@PathVariable("userId") Long userId) {
        UserInfo user = getUserDao().findOne(userId);
        return user;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveUserInfo(@Valid @RequestBody UserInfo user, BindingResult result) {
        return saveUser(user, result);

    }

    @RequestMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity updateUserInfo(@Valid @RequestBody UserInfo user, BindingResult result) {

        return saveUser(user, result);

    }

    private ResponseEntity saveUser(UserInfo user, BindingResult result) {
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

    /**
     * @return the userLogindao
     */
    public UserLoginDao getUserLogindao() {
        return userLogindao;
    }

    /**
     * @param userLogindao the userLogindao to set
     */
    public void setUserLogindao(UserLoginDao userLogindao) {
        this.userLogindao = userLogindao;
    }

    /**
     * @return the userInfoService
     */
    public UserInfoService getUserInfoService() {
        return userInfoService;
    }

    /**
     * @param userInfoService the userInfoService to set
     */
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

}
