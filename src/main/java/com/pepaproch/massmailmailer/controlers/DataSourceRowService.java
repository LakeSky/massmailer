/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class DataSourceRowService {
  @Autowired 
  private DataSourceRowsRep dataSourceRowrep;
  
  public List<DataSourceRow> searchRows(String dataSourceId, PageSpeciFication spec) {
      if(spec.getSearchSpecification().getIndex()==null) {
      return dataSourceRowrep.findByDataSourceIdPage(dataSourceId, spec.getPageable());
      }else {
       return dataSourceRowrep.findByDataSourceIdPaginated(dataSourceId, spec.getSearchSpecification().getIndex(), spec.getSearchSpecification().getSearchString(), spec.getPageable());
      }

  }
}
