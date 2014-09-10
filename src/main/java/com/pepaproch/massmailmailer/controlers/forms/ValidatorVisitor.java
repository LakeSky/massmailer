/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers.forms;

/**
 *
 * @author pepa
 * @param <T>
 */
public interface ValidatorVisitor<T> {
    
    
    
    /**
     *
     * @param t
     */
    public void visit(T t);
}
