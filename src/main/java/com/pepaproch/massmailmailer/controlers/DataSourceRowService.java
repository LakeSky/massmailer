/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataStructure;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.poi.PoiFlatFileHandler;
import com.pepaproch.massmailmailer.poi.PoiHandlerFactory;
import com.pepaproch.massmailmailer.poi.RowMapper;
import com.pepaproch.massmailmailer.poi.RowRecords;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private PoiHandlerFactory poiHandlerFactory;

    public List<DataSourceRow> searchRows(String dataSourceId, PageSpeciFication spec) {
        if (spec.getSearchSpecification().getIndex() == null) {
            return getDataSourceRowrep().findByDataSourceIdPage(dataSourceId, spec.getPageable());
        } else {
            return getDataSourceRowrep().findByDataSourceIdPaginated(dataSourceId, spec.getSearchSpecification().getIndex(), spec.getSearchSpecification().getSearchString(), spec.getPageable());
        }

    }

    public int updateDataFromFile(String dataSourceId, File f) throws IllegalArgumentException {
        PoiFlatFileHandler processor = getPoiHandlerFactory().getHandler(f.getName());
        if (processor == null) {
            throw new IllegalArgumentException("Canoy read this file into datasource rows");
        } else {

            getDataSourceRowrep().delete(getDataSourceRowrep().findByDataSourceId(dataSourceId));
            RowMapper<RowRecords> rowMapper = processor.process(f);
            Collection<DataSourceRow> previewRows = new ArrayList();
            int i = 0;
            for (RowRecords row : rowMapper) {
                if (i > 0) {
                    previewRows.add(new DataSourceRow(dataSourceId, row));
                }
                i++;

            }
            getDataSourceRowrep().save(previewRows);
            return i;
        }

    }

    /**
     * @return the poiHandlerFactory
     */
    public PoiHandlerFactory getPoiHandlerFactory() {
        return poiHandlerFactory;
    }

    /**
     * @param poiHandlerFactory the poiHandlerFactory to set
     */
    public void setPoiHandlerFactory(PoiHandlerFactory poiHandlerFactory) {
        this.poiHandlerFactory = poiHandlerFactory;
    }

    /**
     * @return the dataSourceRowrep
     */
    public DataSourceRowsRep getDataSourceRowrep() {
        return dataSourceRowrep;
    }

    /**
     * @param dataSourceRowrep the dataSourceRowrep to set
     */
    public void setDataSourceRowrep(DataSourceRowsRep dataSourceRowrep) throws IllegalArgumentException {
        this.dataSourceRowrep = dataSourceRowrep;
    }

    DataStructure getDataStructureFrimFile(File file) {

        PoiFlatFileHandler processor = getPoiHandlerFactory().getHandler(file.getName());

        if (processor == null) {
            throw new IllegalArgumentException("Canot read this file into datasource rows");
        } else {
            DataStructure structure = processor.getStructure(processor.process(file));
            structure.setFileName(file.getName());
            return structure;

        }

    }

}
