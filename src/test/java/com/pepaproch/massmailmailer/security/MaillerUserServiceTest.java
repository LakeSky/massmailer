/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.security;

import com.pepaproch.massmailmailer.controlers.forms.LoginForm;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import static junit.framework.Assert.assertEquals;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 *
 * @author pepa
 */
public class MaillerUserServiceTest {

    MaillerUserService userService = new MaillerUserService();
    UserLoginDao userDao = null;
    UserLogin existingUser = null;
    PasswordEncoder encoderRaw = new StandardPasswordEncoder("ThisIsASecretSoChangeMe");
    PasswordEncoder encoder;
    private String expectedPassword;

    @Before
    public void setUp() {
        expectedPassword = encoderRaw.encode("test");
        encoder = mock(PasswordEncoder.class);
        userDao = mock(UserLoginDao.class);
        userService.setPasswordEncoder(encoder);
        existingUser = new UserLogin();
        existingUser.setId(1L);
        existingUser.setUserName("test");
        existingUser.setPassword(expectedPassword);
        userService.setUserDao(userDao);

    }

    public MaillerUserServiceTest() {
    }

    /**
     * Test of loadUserByUsername method, of class MaillerUserService.
     */
    @Test
    public void testLoadUserByUsername() {
        when(userDao.findOne(1L)).thenReturn(existingUser);
        when(userDao.findByUserName("test")).thenReturn(existingUser);
        when(encoder.encode("test")).thenReturn("encodedtest");
        UserLogin result = (UserLogin) userService.loadUserByUsername("test");
        assertEquals(existingUser, result);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {
        when(userDao.findOne(1L)).thenReturn(existingUser);
        when(userDao.findByUserName("test")).thenReturn(existingUser);
        when(encoder.encode("test")).thenReturn("encodedtest");
        System.out.println(expectedPassword);
        UserLogin result = (UserLogin) userService.loadUserByUsername("tester");
        assertEquals(null, result);

    }

    /**
     * Test of saveLoginForm method, of class MaillerUserService.
     */
    @Test
    public void testSaveLoginForm() {
      when(userDao.findOne(1L)).thenReturn(existingUser);
        when(userDao.save(any(UserLogin.class))).thenAnswer(new Answer<UserLogin>() {
            @Override
            public UserLogin answer(InvocationOnMock mc) {
                UserLogin u = (UserLogin) mc.getArguments()[0];
                u.setPassword(expectedPassword);
                u.setId(1L);
                return u;
            }
        });
        LoginForm  loginForm;
        loginForm = new LoginForm();
        loginForm.setLoginId(1L);
        loginForm.setPassword(expectedPassword);
        loginForm.setUserId(1L);
        UserLogin saveLoginForm = (UserLogin) userService.saveLoginForm(loginForm);
        assertEquals(saveLoginForm.getPassword(), expectedPassword);
    }

    /**
     * Test of getUserDao method, of class MaillerUserService.
     */
    @Test
    public void testGetUserDao() {
    }

    /**
     * Test of setUserDao method, of class MaillerUserService.
     */
    @Test
    public void testSetUserDao() {
    }

    /**
     * Test of getPasswordEncoder method, of class MaillerUserService.
     */
    @Test
    public void testGetPasswordEncoder() {
    }

    /**
     * Test of setPasswordEncoder method, of class MaillerUserService.
     */
    @Test
    public void testSetPasswordEncoder() {
    }

}
