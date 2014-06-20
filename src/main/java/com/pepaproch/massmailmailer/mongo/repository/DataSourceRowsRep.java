/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.mongo.repository;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import java.math.BigDecimal;
;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author pepa
 */


public interface DataSourceRowsRep extends CrudRepository<DataSourceRow, String>, PagingAndSortingRepository<DataSourceRow, String> {

    @Query("  { $and: [ {dataSourceId: ?0}, { dataSourceFields: { $elemMatch: { index: ?1, value: ?2 } } }]} ")
    public Collection<DataSourceRow> findByColumnValue(String dataSourceId, Integer columnIndex, Object value);

    @Query("  { dataSourceId: ?0} ")
    public Collection<DataSourceRow> findByDataSourceId(String dataSourceId);

    @Query("{ $and: [ { dataSourceId: ?0 } , {order: ?1}] }")
    public DataSourceRow findByDataSourceIdOrder(String dataSourceId, Long order);

    @Query("  { dataSourceId: ?0} ")
    public List<DataSourceRow> findByDataSourceIdPage(String dataSourceId, Pageable pageable);

    @Query(value = "  { dataSourceId: ?0} ", count = true)
    public Long countByDataSourceId(String dataSourceId);

    /**
     *
     * @param dataSourceId
     * @param columnIndex
     * @param value
     * @param pageable
     * @return
     */
    @Query("  { $and: [ {dataSourceId: ?0}, { dataSourceFields: { $elemMatch: { index: ?1, value:{$regex : ?2 } } } }]} ")
    public List<DataSourceRow> findByDataSourceIdPaginated(String dataSourceId, Integer columnIndex, Object value, Pageable pageable);

}
