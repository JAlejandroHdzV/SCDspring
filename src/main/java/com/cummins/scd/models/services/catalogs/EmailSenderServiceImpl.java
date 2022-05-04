package com.cummins.scd.models.services.catalogs;

import java.io.File;
import java.math.BigInteger;
import java.util.Locale;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cummins.scd.models.dao.IDesQuejasNotDao;
import com.cummins.scd.models.entity.Quejas_Not;

@Service
public class EmailSenderServiceImpl implements IEmailSenderService {

	
	@Autowired
    private JavaMailSender mailSender;
 
	@Autowired
    private IDesQuejasNotDao quejasDao;
	
	//---------------------------------------------------------
	//						LABELS
	//--------------------------------------------------------
	private static final String LABEL= "text.email.complaints.pdf.";
	private static final String TITLE= LABEL.concat("title");
	private static final String FOLIO= LABEL.concat("folio");
	private static final String NAME_FILE= LABEL.concat("nameFile");
	private static final String SERVICE_POINT= LABEL.concat("servicePoint");
	
	
	
	
    @Override
    public  String sendSimpleMail(String []mailTo, String []copyTo, String lang, String idQueja, String spCode, DataSource dataSource, Locale locale, MessageSource messageSource  ) throws Exception {
    	
    	try {
    		
        String title =messageSource.getMessage(TITLE, null, locale);
        String folio =messageSource.getMessage(FOLIO, null, locale);
        String nameFile =messageSource.getMessage(NAME_FILE, null, locale).concat(".pdf");
        String servicePoint =messageSource.getMessage(SERVICE_POINT, null, locale);
    	System.out.println("preparando email");
    	Quejas_Not quejas= quejasDao.findOne(BigInteger.valueOf(1));
    	String asunto="SCD-".concat(title).concat(". ").concat(folio).concat(idQueja).concat(". ").concat(servicePoint).concat(spCode).concat(".");
    	String body="";
    	
    	switch(lang) {
    	case "es_ES": 
    		body=quejas.getMailEsp();
    		break;
    	case "en_US": 
    		body=quejas.getMailIng();
    		break;
    	case "pt_BR": 
    		body=quejas.getMailPor();
    		break;
    	}
                 
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("DES@cummins.com");
        helper.setTo(mailTo);
        if(copyTo!=null) {
        helper.setCc(copyTo);
        }
                  helper.setSubject (asunto);
                  helper.setText (body,true);
                  helper.addAttachment (nameFile,dataSource);
  
        /*FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
                  helper.addAttachment ();
                  ;*/
  
        mailSender.send(mimeMessage);
        return"send";
    	}catch(Exception e) {
    		return e.getMessage();
    	}
    }

}
