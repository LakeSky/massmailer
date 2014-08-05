/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.security;

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
    UserDao userDao = null;
    User existingUser = null;
    PasswordEncoder encoderRaw = new StandardPasswordEncoder("ThisIsASecretSoChangeMe");
    PasswordEncoder encoder;
    private String expectedPassword;

    @Before
    public void setUp() {
        expectedPassword = encoderRaw.encode("test");
        encoder = mock(PasswordEncoder.class);
        userDao = mock(UserDao.class);
        userService.setPasswordEncoder(encoder);
        existingUser = new User();
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
        User result = (User) userService.loadUserByUsername("test");
        assertEquals(existingUser, result);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {
        when(userDao.findOne(1L)).thenReturn(existingUser);
        when(userDao.findByUserName("test")).thenReturn(existingUser);
        when(encoder.encode("test")).thenReturn("encodedtest");
        System.out.println(expectedPassword);
        User result = (User) userService.loadUserByUsername("tester");
        assertEquals(null, result);

    }

    /**
     * Test of saveLoginForm method, of class MaillerUserService.
     */
    @Test
    public void testSaveLoginForm() {
        when(userDao.save(any(User.class))).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock mc) {
                User u = (User) mc.getArguments()[0];
                u.setId(1L);
                return u;
            }
        });
        UserForm uf = new UserForm();
        uf.setName("test");
        uf.setPlainPassword("test");
        User saveLoginForm = (User) userService.saveLoginForm(uf);
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
