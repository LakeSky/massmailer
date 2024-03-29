/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.security;


import com.pepaproch.massmailmailer.controlers.forms.LoginForm;
import com.pepaproch.massmailmailer.repository.UserInfoDao;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class MaillerUserService implements UserDetailsService {

    @Autowired
    private UserLoginDao userDao;
    
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *
     * @param string
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserLogin loadUserByUsername(String string) throws UsernameNotFoundException {
       UserLogin ud = userDao.findByUserName(string);
        if (ud == null) {
            throw new UsernameNotFoundException("User not found"); //To change body of generated methods, choose Tools | Templates.
        } else {
            return ud;
        }
    }

    public UserLogin saveLoginForm(LoginForm loginForm) {
        UserLogin u = null;

        if (loginForm.getLoginId() == null) {
            u = new UserLogin(loginForm.getUserName(),loginForm.getPassword(),userInfoDao.findOne(loginForm.getUserId()));    
          
        } else {
            u = userDao.findOne(loginForm.getLoginId());
            u.setUserName(loginForm.getUserName());
            u.setPassword(passwordEncoder.encode(loginForm.getPassword()));
        }
        return userDao.save(u);
    }

    /**
     * @return the userDao
     */
    public UserLoginDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserLoginDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @return the passwordEncoder
     */
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * @param passwordEncoder the passwordEncoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @return the userInfoDao
     */
    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    /**
     * @param userInfoDao the userInfoDao to set
     */
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

}
