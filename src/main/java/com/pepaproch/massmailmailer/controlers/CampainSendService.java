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
import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.mail.mailgun.MailGunRestClient;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.poi.DocumentFactory;
import com.pepaproch.massmailmailer.poi.DocumentFactoryImpl;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.poi.convert.WordDocument;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import java.io.IOException;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class CampainSendService {

    @Autowired
    private DataSourceRowsRep rowsrepository;
    @Autowired
    private CampainService campainService;
    @Autowired
    private TemlateService templateService;
    @Autowired
    private DataSourceInfoRep dataSourceRep;
    @Autowired
    private ConvertService convertService;
    @Autowired
    private EmailRepo emailrepo;
    @Autowired 
    private MailGunRestClient mailgunClient;

    
    @Async
    public void processCampain(Campain c) throws IOException {
        c.setStatus("CREATING");
        campainService.getCampainRepo().save(c);
        DataStructure ds = getDataSourceRep().findOne(c.getDataSourceId()).getDataStructure();
        Collection<DataSourceRow> findByDataSourceId = getRowsrepository().findByDataSourceId(c.getDataSourceId());

        TextDocumentHolder emailText = new HtmlDocument(new StringPlaceHolderHelper("###"), c.getEmailText());
        TextDocumentHolder emailRec = new HtmlDocument(new StringPlaceHolderHelper("###"), "###" + c.getRecipients() + "###");
        TextDocumentHolder emailSubject = new HtmlDocument(new StringPlaceHolderHelper("###"), c.getSubject());
        DocumentHolder emailAttachmentdocu = null;
        if (c.getCustomizeAttachments()) {
                    DocumentFactory documentFactory = new DocumentFactoryImpl();
        emailAttachmentdocu = documentFactory.getDocument("/tmp/" + c.getAttachmentFileSystemName(),new StringPlaceHolderHelper("###"));


        }

        for (DataSourceRow r : findByDataSourceId) {

            MailRecordBulder mlBulder = new DefaultMailRecordBulder();
            mlBulder.setFrom("pepaproch@gmail.com");
            mlBulder.setEmailContent(proccesEmailBody(emailText, ds, r));
            mlBulder.setReccipients(proccesEmailBody(emailRec, ds, r), null, null);
            mlBulder.setSubject(proccesEmailBody(emailSubject, ds, r));
            byte[] proccesAttachment = proccesAttachment(emailAttachmentdocu, ds, r);
            byte[] convert = getConvertService().convert(proccesAttachment, "doc", "pdf", Boolean.TRUE);
            mlBulder.setAttachment(convert, c.getAttachmentOutputName(), c.getAttachmentOutputType());
            mlBulder.setCampain(c);
            Email save = emailrepo.save(mlBulder.getEmail());
            mailgunClient.sendEmail(save);

        }
        c.setStatus("SEND");
        campainService.getCampainRepo().save(c);
    }

    public String proccesEmailBody(TextDocumentHolder emailText, DataStructure ds, DataSourceRow r) {

        return getTemplateService().populateTextTemplate(emailText, ds, r);

    }

    public byte[] proccesAttachment(DocumentHolder document, DataStructure ds, DataSourceRow r) {

        return getTemplateService().populateTemplateOutputStream(document, ds, r);
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

    /**
     * @return the templateService
     */
    public TemlateService getTemplateService() {
        return templateService;
    }

    /**
     * @param templateService the templateService to set
     */
    public void setTemplateService(TemlateService templateService) {
        this.templateService = templateService;
    }

    /**
     * @return the dataSourceRep
     */
    public DataSourceInfoRep getDataSourceRep() {
        return dataSourceRep;
    }

    /**
     * @param dataSourceRep the dataSourceRep to set
     */
    public void setDataSourceRep(DataSourceInfoRep dataSourceRep) {
        this.dataSourceRep = dataSourceRep;
    }

    /**
     * @return the convertService
     */
    public ConvertService getConvertService() {
        return convertService;
    }

    /**
     * @param convertService the convertService to set
     */
    public void setConvertService(ConvertService convertService) {
        this.convertService = convertService;
    }

    /**
     * @return the emailrepo
     */
    public EmailRepo getEmailrepo() {
        return emailrepo;
    }

    /**
     * @param emailrepo the emailrepo to set
     */
    public void setEmailrepo(EmailRepo emailrepo) {
        this.emailrepo = emailrepo;
    }

    /**
     * @return the mailgunClient
     */
    public MailGunRestClient getMailgunClient() {
        return mailgunClient;
    }

    /**
     * @param mailgunClient the mailgunClient to set
     */
    public void setMailgunClient(MailGunRestClient mailgunClient) {
        this.mailgunClient = mailgunClient;
    }
}
