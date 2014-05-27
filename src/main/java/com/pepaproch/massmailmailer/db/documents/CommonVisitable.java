/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

/**
 *
 * @author pepa
 * @param <T>
 */
public interface CommonVisitable <T>{
    public  void visit(CommonVisitor<T> t);
}
