/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document(collection = "datastructuresfields")
class DataStructureField {
    private String id;
    private Integer index;
    private String name;
   
}
