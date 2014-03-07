/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageSpecBuilderMongo implements PageSpecBuilder {

    private Integer searchIndex;
    private String searchString = null;

    private Integer sortIndex = 0;
    private Integer sortDirection = 1;

    private Integer pageNumber = 1;
    private Integer limit= 10;
    private final String mongoRepository;



    @Override
    public PageSpeciFication getPageSpecification() {
        PageSpeciFication spec = new PageSpeciFication();
        spec.setSearchSpecification(new SearchSpecification(searchIndex, searchString) );
        Sort sort= new Sort((sortDirection==1)? Sort.Direction.ASC : Sort.Direction.DESC, mongoRepository +"."+sortIndex+".value");
        Pageable pageable = new  PageRequest((pageNumber==null)? 1 : pageNumber,(limit==null)? 10: limit, sort);
  
        return spec;
    }

    public PageSpecBuilderMongo(String mongoRepository) {
        this.searchIndex = null;
     this.mongoRepository = mongoRepository;
    }

    
    /**
     *
     * @param index
     * @param direction
     * @return 
     */
    @Override
    public PageSpecBuilder setSort(Object index, Object direction) {
   this.sortIndex = (index==null)? 1:  (Integer) index;
   this.sortDirection = (direction==null)? 1: (Integer) direction;
     return this;
    }

    @Override
    public PageSpecBuilder setPage(Integer page, Integer limit) {
      this.limit = limit;
      this.pageNumber = page;
        return this;
    }

    @Override
    public PageSpecBuilder setSearch(Object index, String searchString) {
      this.searchIndex = (Integer) index;
      this.searchString = searchString;
      return this;
    }

}
