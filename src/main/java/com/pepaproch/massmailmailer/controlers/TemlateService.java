/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.documents.DataSource;
import com.pepaproch.massmailmailer.db.documents.DataSourceField;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataStructure;
import com.pepaproch.massmailmailer.db.documents.DataStructureMetaField;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.poi.convert.TemplateDataItem;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.PlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.Template;
import com.pepaproch.massmailmailer.poi.convert.TemplateImpl;
import com.pepaproch.massmailmailer.poi.convert.TemplateMeta;
import com.pepaproch.massmailmailer.poi.convert.WordDocument;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class TemlateService {

    @Autowired
    private DataSourceRowsRep dataSourceRowsRep;
    @Autowired
    private DataSourceInfoRep dataSourceInfoRep;

    public String createPreview(String templateFile, String dataSourceId) throws IOException {
        DataSource ds = dataSourceInfoRep.findOne(dataSourceId);
        Sort sortable = new Sort(Sort.Direction.ASC, "dataSourceFields." + 0 + ".value");
        Pageable pageSpecification = new PageRequest(1, 1, sortable);

        Collection<DataSourceRow> findByDataSourceIdPage = (List<DataSourceRow>) dataSourceRowsRep.findByDataSourceIdPage(dataSourceId, pageSpecification);
        if(findByDataSourceIdPage==null || findByDataSourceIdPage.size()==0) {
        throw new IllegalArgumentException("DataSource not found: " + dataSourceId);
        }
        DocumentHolder docu = new WordDocument(templateFile,new StringPlaceHolderHelper("####"));
        return populateTemplate(docu,ds.getDataStructure() , findByDataSourceIdPage.iterator().next());
    }

    public String populateTemplate(DocumentHolder docu, DataStructure ds, DataSourceRow row) {
        TemplateMeta templateMeta = docu.getTemplateMeta();
        Collection<String> placeHolders = templateMeta.getPlaceHolders();
        Collection<DataStructureMetaField> usedFields = new ArrayList<DataStructureMetaField>();
        for (DataStructureMetaField mf : ds.getDataStructureFields()) {
            if (placeHolders.contains("####" + mf.getName() +"####")) {
                usedFields.add(mf);
            }

        }

        List<TemplateDataItem> items = new ArrayList();
        Collection<DataSourceField> dataSourceFields = row.getDataSourceFields();
        DataSourceField[] array = new DataSourceField[dataSourceFields.size()];
        dataSourceFields.toArray(array);
        TemplateDataItem dat = new TemplateDataItem();
        for (DataStructureMetaField mf : usedFields) {
            dat.add("####" + mf.getName() + "####", array[mf.getIndex()].stringValue());
            items.add(dat);
        }
        Template template = new TemplateImpl(docu, dat);
        template.procces("/tmp/" + row.getDataSourceId() + row.getId() + docu.getTemplateMeta().getFileName());
        return row.getDataSourceId() + row.getId() + docu.getTemplateMeta().getFileName();

    }

    /**
     * @return the dataSourceRowsRep
     */
    public DataSourceRowsRep getDataSourceRowsRep() {
        return dataSourceRowsRep;
    }

    /**
     * @param dataSourceRowsRep the dataSourceRowsRep to set
     */
    public void setDataSourceRowsRep(DataSourceRowsRep dataSourceRowsRep) {
        this.dataSourceRowsRep = dataSourceRowsRep;
    }

    /**
     * @return the dataSourceInfoRep
     */
    public DataSourceInfoRep getDataSourceInfoRep() {
        return dataSourceInfoRep;
    }

    /**
     * @param dataSourceInfoRep the dataSourceInfoRep to set
     */
    public void setDataSourceInfoRep(DataSourceInfoRep dataSourceInfoRep) {
        this.dataSourceInfoRep = dataSourceInfoRep;
    }
}
