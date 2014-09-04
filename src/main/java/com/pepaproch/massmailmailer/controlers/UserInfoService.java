/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.AbstracSpec;
import com.pepaproch.massmailmailer.db.entity.UserInfo;
import com.pepaproch.massmailmailer.db.entity.UserInfoSpecification;
import com.pepaproch.massmailmailer.repository.UserInfoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserInfoDao  iserInfoRepo;


  



    List<UserInfo> searchAll(Pageable pageSpecification,  String searchString) {
        if (null != searchString) {
            return getIserInfoRepo().findAll(UserInfoSpecification.findByUserName(searchString, AbstracSpec.LIKE_COMPARE), pageSpecification).getContent();
        }else {
            return getIserInfoRepo().findAll(pageSpecification).getContent();
        }
        
    }

    /**
     * @return the iserInfoRepo
     */
    public UserInfoDao getIserInfoRepo() {
        return iserInfoRepo;
    }

    /**
     * @param iserInfoRepo the iserInfoRepo to set
     */
    public void setIserInfoRepo(UserInfoDao iserInfoRepo) {
        this.iserInfoRepo = iserInfoRepo;
    }

 

}
