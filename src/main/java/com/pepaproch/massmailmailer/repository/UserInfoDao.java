/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;


import com.pepaproch.massmailmailer.db.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author pepa
 */

public interface UserInfoDao extends PagingAndSortingRepository<UserInfo, Long>,JpaRepository<UserInfo, Long> ,JpaSpecificationExecutor<UserInfo>{

}
