/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import org.springframework.data.domain.Pageable;

/**
 *
 * @author pepa
 */
public class PageSpeciFication {
   private  Pageable pageable;
   private SearchSpecification searchSpecification;

    /**
     * @return the pageable
     */
    public Pageable getPageable() {
        return pageable;
    }

    /**
     * @param pageable the pageable to set
     */
    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    /**
     * @return the searchSpecification
     */
    public SearchSpecification getSearchSpecification() {
        return searchSpecification;
    }

    /**
     * @param searchSpecification the searchSpecification to set
     */
    public void setSearchSpecification(SearchSpecification searchSpecification) {
        this.searchSpecification = searchSpecification;
    }
    
}
