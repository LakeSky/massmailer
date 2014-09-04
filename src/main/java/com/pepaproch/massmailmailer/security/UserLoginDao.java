/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pepa
 */
public interface UserLoginDao extends JpaRepository<UserLogin, Long> {

    UserLogin findByUserName(String name);

    /**
     *
     * @param userInfoId
     * @return
     */
    @Query("SELECT l FROM UserLogin l WHERE l.userInfo.id  = :userInfoId")
    UserLogin getByUserInfoId(@Param("userInfoId") Long userInfoId);

   

}
