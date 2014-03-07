/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

/**
 *
 * @author pepa
 */
public interface PageSpecBuilder {
    public PageSpecBuilder setSort(Object index, Object direction);
    public PageSpecBuilder setPage(Integer page, Integer limit);

    /**
     *
     * @param index
     * @param searchString
     * @return 
     */
    public PageSpecBuilder setSearch(Object index, String searchString);
    public PageSpeciFication getPageSpecification() ;
    
}
