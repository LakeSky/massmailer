/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.db.documents;

import java.util.Collection;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author pepa
 */
@Document(collection = "datastructures")
class DataStructureInfo {
    
    @Id
    private String id;
    private Collection<DataStructureField> dataStructureFields; 
    
}
