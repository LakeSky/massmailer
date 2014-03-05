/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.DefaultMailRecordBulder;
import com.pepaproch.massmailmailer.db.HtmlDocument;
import com.pepaproch.massmailmailer.db.MailRecordBulder;
import com.pepaproch.massmailmailer.db.TextDocumentHolder;
import com.pepaproch.massmailmailer.db.documents.DataSourceRow;
import com.pepaproch.massmailmailer.db.documents.DataStructure;
import com.pepaproch.massmailmailer.db.entity.Campain;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.WordDocument;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
public class CampainSendService {

    @Autowired
    private DataSourceRowsRep rowsrepository;
    @Autowired
    private CampainService campainService;
    @Autowired
    private TemlateService templateService;
    @Autowired
    private DataSourceInfoRep dataSourceRep;

    public void processCampain(BigDecimal id) throws IOException {
        Campain c = campainService.findOne(id);
        DataStructure ds = dataSourceRep.findOne(c.getDataSourceId()).getDataStructure();
        Collection<DataSourceRow> findByDataSourceId = rowsrepository.findByDataSourceId(c.getDataSourceId());
        TextDocumentHolder emailText = new HtmlDocument(new StringPlaceHolderHelper("####"), c.getEmailText());
        DocumentHolder emailAttachmentdocu = null;
        if (c.getCustomizeAttachments()) {
            emailAttachmentdocu = new WordDocument(c.getAttachmentFileSystemName(), new StringPlaceHolderHelper("####"));

        }

        for (DataSourceRow r : findByDataSourceId) {
            MailRecordBulder mlBulder = new DefaultMailRecordBulder();
            mlBulder.setFrom("pepaproch@gmail.com");
            mlBulder.setEmailContent(templateService.populateTextTemplate(emailText, ds, r));
        }
    }

    /**
     * @return the rowsrepository
     */
    public DataSourceRowsRep getRowsrepository() {
        return rowsrepository;
    }

    /**
     * @param rowsrepository the rowsrepository to set
     */
    public void setRowsrepository(DataSourceRowsRep rowsrepository) {
        this.rowsrepository = rowsrepository;
    }

    /**
     * @return the campainService
     */
    public CampainService getCampainService() {
        return campainService;
    }

    /**
     * @param campainService the campainService to set
     */
    public void setCampainService(CampainService campainService) {
        this.campainService = campainService;
    }
}
