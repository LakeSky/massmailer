/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.repository;

import com.pepaproch.massmailmailer.db.entity.Users;
import java.math.BigDecimal;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pepa
 */

public interface UserRepo extends CrudRepository<Users,BigDecimal>{
    
}
