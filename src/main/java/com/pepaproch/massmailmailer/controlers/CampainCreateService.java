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
import com.pepaproch.massmailmailer.db.entity.CampainAttachment;
import com.pepaproch.massmailmailer.db.entity.Email;
import com.pepaproch.massmailmailer.db.entity.EmailFolder;
import com.pepaproch.massmailmailer.mail.mailgun.MailGunRestClient;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceInfoRep;
import com.pepaproch.massmailmailer.mongo.repository.DataSourceRowsRep;
import com.pepaproch.massmailmailer.poi.DocumentFactory;
import com.pepaproch.massmailmailer.poi.DocumentFactoryImpl;
import com.pepaproch.massmailmailer.poi.convert.DocumentHolder;
import com.pepaproch.massmailmailer.poi.convert.SimpleDocument;
import com.pepaproch.massmailmailer.poi.convert.StringPlaceHolderHelper;
import com.pepaproch.massmailmailer.repository.EmailFolderRepo;
import com.pepaproch.massmailmailer.repository.EmailRepo;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.WeakHashMap;
import java.util.concurrent.Future;
import javax.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author pepa
 */
@Service
@Transactional
public class CampainCreateService {

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
    private EmailFolderRepo emailFoldeRepo;
    @Autowired
    private MailGunRestClient mailgunClient;

    /**
     *
     * @param c
     * @return
     * @throws IOException
     */
    @Async
    public Future<Void> processCampain(Campain c) throws IOException {
        c.setStatus("CREATING");
        campainService.getCampainRepo().save(c);
        DataStructure ds = getDataSourceRep().findOne(c.getDataSourceId()).getDataStructure();
        Collection<DataSourceRow> findByDataSourceId = getRowsrepository().findByDataSourceId(c.getDataSourceId());

        TextDocumentHolder emailText = new HtmlDocument(new StringPlaceHolderHelper("###"), c.getEmailText());
        TextDocumentHolder emailRec = new HtmlDocument(new StringPlaceHolderHelper("###"), "###" + c.getRecipients() + "###");
        TextDocumentHolder emailSubject = new HtmlDocument(new StringPlaceHolderHelper("###"), c.getSubject());
        DocumentHolder emailAttachmentdocu = null;
        WeakHashMap<Long, Object> attachments = new WeakHashMap<>();

        for (CampainAttachment at : c.getCampainAttachments()) {
            if (at.getCustomizeAttachments()) {
                DocumentFactory documentFactory = new DocumentFactoryImpl();
                emailAttachmentdocu = documentFactory.getDocument("/tmp/" + at.getAttachmentFileSystemName(), new StringPlaceHolderHelper("###"));
                attachments.put(at.getId(), emailAttachmentdocu);
            } else {
                attachments.put(at.getId(), new  SimpleDocument(new File("/tmp/" + at.getAttachmentFileSystemName())));
            }
        }
        
        EmailFolder emailFolder = emailFoldeRepo.findByEmailFolderId(EmailFolder.FOLDER_OUTGOING);
        int i = 1;
        for (DataSourceRow r : findByDataSourceId) {
            MailRecordBulder mlBulder = new DefaultMailRecordBulder(emailFolder);
            mlBulder.setFrom("pepaproch@gmail.com");
            mlBulder.setEmailContent(proccesEmailBody(emailText, ds, r));
//            mlBulder.setReccipients(proccesEmailBody(emailRec, ds, r), null, null);
            mlBulder.setReccipients("pepaproch@gmail.com", null, null);
            mlBulder.setSubject(proccesEmailBody(emailSubject, ds, r));
            for (CampainAttachment at : c.getCampainAttachments()) {
                byte[] finalAttachment = null;
                byte[] proccesAttachment = null;

                if (at.getCustomizeAttachments() && attachments.get(at.getId()) instanceof DocumentHolder) {
                    proccesAttachment = proccesAttachment((DocumentHolder) attachments.get(at.getId()), ds, r);

                } else if (attachments.get(at.getId()) instanceof SimpleDocument) {
                    proccesAttachment = ((SimpleDocument)attachments.get(at.getId())).getOutputStream();
                }
                if (!at.getAttachmentFileType().equalsIgnoreCase(at.getAttachmentOutputType())) {
                    finalAttachment = getConvertService().convert(proccesAttachment, at.getAttachmentFileType(), at.getAttachmentOutputType(), Boolean.TRUE);
                } else {
                    finalAttachment = proccesAttachment;
                }

                mlBulder.setAttachment(finalAttachment, at.getAttachmentOutputName(), at.getAttachmentOutputType());

            }
           
            mlBulder.setCampain(c);
            mlBulder.setCampainIndex(Long.valueOf(i));
            System.out.println("EMAIL CREATED: " + i);
            i++;
            Email save = emailrepo.saveAndFlush(mlBulder.getEmail());

        }
        c.setStatus("SENDING");
        campainService.getCampainRepo().save(c);
    }
    
    
    private  Email buildEmail() {
        return null;
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

    /**
     * @return the emailFoldeRepo
     */
    public EmailFolderRepo getEmailFoldeRepo() {
        return emailFoldeRepo;
    }

    /**
     * @param emailFoldeRepo the emailFoldeRepo to set
     */
    public void setEmailFoldeRepo(EmailFolderRepo emailFoldeRepo) {
        this.emailFoldeRepo = emailFoldeRepo;
    }

    Email geCreatePreview(BigDecimal campainId, BigDecimal emailIdx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
