package com.cummins.scd.util;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.springframework.context.MessageSource;

import com.cummins.scd.controllers.catalogs.DesQuejasController;
import com.cummins.scd.models.dto.QuejasDTO;


public class pdfBuilder {
	private static final Logger logger = Logger.getLogger(pdfBuilder.class);
	
	//---------------------------------------------------------
	//						LABELS
	//--------------------------------------------------------
	private static final String LABEL= "text.email.complaints.pdf.";
	private static final String TITLE= LABEL.concat("title");
	private static final String FOLIO= LABEL.concat("folio");
	private static final String STATUS= LABEL.concat("status");
	private static final String DEALER= LABEL.concat("dealer");
	private static final String DEALER_CODE= LABEL.concat("dealerCode");
	private static final String DISTRIBUITOR= LABEL.concat("distribuitor");;
	private static final String CLIENT= LABEL.concat("client");
	private static final String CONTACT= LABEL.concat("contact");
	private static final String COMPLAINT= LABEL.concat("complaint");
	private static final String DESCRIPTION_COMPLAINT= LABEL.concat("descComplaint");
	private static final String FOLLOW_UP_COMPLAINT= LABEL.concat("followUpComplaint");
	private static final String RESPONSIBLE_COMPLAINT= LABEL.concat("responsibleComplaint");
	private static final String ENGAGEMENT_COMPLAINT= LABEL.concat("engagementComplaint");
	private static final String DATE_COMPLAINT= LABEL.concat("dateComplaint");
	private static final String DATE_CLOSE= LABEL.concat("dateClose");
	private static final String DATE_CAPTURE= LABEL.concat("dateCapture");
	private static final String AUDIT= LABEL.concat("audit");
	private static final String REPORT= LABEL.concat("report");
	private static final String NAME_FILE= LABEL.concat("nameFile");
	private static final String ACTIVE= LABEL.concat("active");
	private static final String INACTIVE= LABEL.concat("inactive");
	
	public ByteArrayOutputStream createPdfDocument(String path, QuejasDTO queja, MessageSource messageSource, Locale locale) throws IOException {
		
		
		
		System.out.println(queja.toString());
		PDDocument doc = new PDDocument();
        PDPage myPage = new PDPage();
        String FILE_NAME=NAME_FILE.concat(".pdf") ;
        try {
        doc.addPage(myPage);
        	PDPageContentStream cont = new PDPageContentStream(doc, myPage);
        	PDRectangle mediaBox = myPage.getMediaBox();
        	PDFont fontTimes = PDType1Font.HELVETICA_BOLD;
            cont.setFont(fontTimes, 20);
        	AffineTransform at = new AffineTransform(50, 0, 0, 50, 25, 700);
        	Matrix m = new Matrix(at);
        	PDImageXObject pdImage = PDImageXObject.createFromFile(path, doc);
        	cont.drawImage(pdImage, m);
        	int line =0;
        	
        	//-----------------------------------------------------------------------
            //							DISTRIBUTION
            //-----------------------------------------------------------------------
        	cont.beginText();
            cont.setFont(fontTimes, 16);
            String dist = "Distribution";
            cont.newLineAtOffset(80, 730);
            System.out.println("label distribution: "+50*(++line));
            cont.setNonStrokingColor(Color.RED);
            cont.showText(dist);
            cont.endText();
        	
        	
        	
            //-----------------------------------------------------------------------
            //							TITLE
            //-----------------------------------------------------------------------
            cont.beginText();
            cont.setFont(fontTimes, 20);
            String title = messageSource.getMessage(TITLE, null, locale);
            float titleWidth = fontTimes.getStringWidth(title) / 1000 * 20;
            float startX = (mediaBox.getWidth() - titleWidth) / 2;
            cont.newLineAtOffset(startX, mediaBox.getHeight() - (50*(++line)+50));
            System.out.println("label notificacion de quejas: "+50*(++line));
            cont.setNonStrokingColor(Color.GRAY);
            cont.showText(title);
            cont.endText();
            
            //-----------------------------------------------------------------------
            //						HEADER FOLIO AND STATUS
            //-----------------------------------------------------------------------
            float headerFolio=  50*(++line);
            
            String folio=messageSource.getMessage(FOLIO, null, locale);
            String numberFolio=queja.getIdQueja().toString()!=null?queja.getIdQueja().toString():"";
            float folioWidth = (fontTimes.getStringWidth(folio) / 1000 * 10);
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(headerFolio+20));
            System.out.println("label folio: "+headerFolio+40);
            cont.setNonStrokingColor(Color.RED);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(folio);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+folioWidth+3, mediaBox.getHeight()-(headerFolio+20));
            System.out.println("numberFolio: "+numberFolio+" "+headerFolio+40);
            cont.setNonStrokingColor(Color.BLACK);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(numberFolio);
            cont.endText();
            
            
            String stTitle=messageSource.getMessage(STATUS, null, locale);
            String status=queja.getEstatus()!=null?queja.getEstatus().equals("Y")?messageSource.getMessage(ACTIVE, null, locale):messageSource.getMessage(INACTIVE, null, locale):"";
            float statusTitleWidth = (fontTimes.getStringWidth(stTitle) / 1000 * 10)+3;
            float statusStWidth = (fontTimes.getStringWidth(status) / 1000 * 10);
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-statusTitleWidth-statusStWidth, mediaBox.getHeight()-(headerFolio+20));
            System.out.println("Label status: "+headerFolio+20);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.setNonStrokingColor(Color.BLACK);
            cont.showText(stTitle);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-statusStWidth, mediaBox.getHeight()-(headerFolio+20));
            System.out.println("status: "+status+" "+headerFolio+20);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(status);
            cont.endText();
            
            
            //-----------------------------------------------------------------------
            //						HEADER DEALER
            //-----------------------------------------------------------------------
            String dealer=messageSource.getMessage(DEALER, null, locale);
            String nameDelaer=queja.getPuntoDeServicio()!=null?queja.getPuntoDeServicio():"";
            float dealerWidth = (fontTimes.getStringWidth(dealer) / 1000 * 10);
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(headerFolio+40));
            System.out.println("label dealer: "+headerFolio+40);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(dealer);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+dealerWidth+3, mediaBox.getHeight()-(headerFolio+40));
            System.out.println("nameDealer: "+nameDelaer+" "+headerFolio+40);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(nameDelaer);
            cont.endText();
            
            String codeDealer=messageSource.getMessage(DEALER_CODE, null, locale);
            String code=queja.getCodigoPuntoServicio()!=null?queja.getCodigoPuntoServicio():"";
            float codeDealerWidth = (fontTimes.getStringWidth(codeDealer) / 1000 * 10)+3;
            float codeWidth = (fontTimes.getStringWidth(code) / 1000 * 10);
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-codeDealerWidth-codeWidth, mediaBox.getHeight()-(headerFolio+40));
            System.out.println("label codigo Dealer: "+headerFolio+40);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(codeDealer);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-codeWidth, mediaBox.getHeight()-(headerFolio+40));
            System.out.println("code: "+code+" "+headerFolio+40);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(code);
            cont.endText();
            
            //-----------------------------------------------------------------------
            //						HEADER DISTRIBUITOR
            //-----------------------------------------------------------------------
            String distributor=messageSource.getMessage(DISTRIBUITOR, null, locale);
            String nameDistributor=queja.getDr()!=null?queja.getDr():"";
            float distributorWidth = (fontTimes.getStringWidth(distributor) / 1000 * 10);
            
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(headerFolio+60));
            System.out.println("label distributor: "+headerFolio+60);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(distributor);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+distributorWidth+3, mediaBox.getHeight()-(headerFolio+60));
            System.out.println("nameDistributor: "+nameDistributor+" "+headerFolio+60);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(nameDistributor);
            cont.endText();
            
            
            String oem="OEM: ";
            String oemName=queja.getOem()!=null?queja.getOem():"";
            float oemWidth = (fontTimes.getStringWidth(oem) / 1000 * 10)+3;
            float oemNameWidth = (fontTimes.getStringWidth(oemName) / 1000 * 10);  
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-oemWidth-oemNameWidth, mediaBox.getHeight()-(headerFolio+60));
            System.out.println("label oem: "+headerFolio+60);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(oem);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-oemNameWidth, mediaBox.getHeight()-(headerFolio+60));
            System.out.println("oemName: "+oemName+" "+headerFolio+60);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(oemName);
            cont.endText();
            
            //-----------------------------------------------------------------------
            //						HEADER CLIENTE
            //-----------------------------------------------------------------------
            String client=messageSource.getMessage(CLIENT, null, locale);
            String clientName=queja.getCliente()!=null?queja.getCliente():"";
            float clientWidth = (fontTimes.getStringWidth(client) / 1000 * 10);
            
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(headerFolio+80));
            System.out.println("label client: "+headerFolio+80);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(client);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+clientWidth+3, mediaBox.getHeight()-(headerFolio+80));
            System.out.println("clientName: "+clientName+" "+headerFolio+80);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(clientName);
            cont.endText();
            
            
            //-----------------------------------------------------------------------
            //						HEADER CONTACT
            //-----------------------------------------------------------------------
            String contact=messageSource.getMessage(CONTACT, null, locale);
            String contactName=queja.getContactoQueja()!=null?queja.getContactoQueja():"";
            float contactWidth = (fontTimes.getStringWidth(contact) / 1000 * 10);
            
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(headerFolio+100));
            System.out.println("label contact: "+headerFolio+100);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(contact);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+contactWidth+3, mediaBox.getHeight()-(headerFolio+100));
            System.out.println("contactName: "+contactName+" "+headerFolio+100);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(contactName);
            cont.endText();
           
            
            //-----------------------------------------------------------------------
            //						BODY COMPLAINT
            //-----------------------------------------------------------------------
            float bodyC= headerFolio+130;
            //cont.beginText();
            //cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=20));
            System.out.println(bodyC);
            String bodyComplaintLabel = messageSource.getMessage(COMPLAINT, null, locale);
            String bodyComplaintEntity = queja.getQueja()!=null?queja.getQueja():"";
            float bodyComplaintLabelWidth = (fontTimes.getStringWidth(bodyComplaintLabel) / 1000 * 10);
            //String bodyComplaint = bodyComplaintLabel.concat(bodyComplaintEntity);
            //cont.showText(bodyComplaint);
            //cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(bodyC+=20));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(bodyComplaintLabel);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+bodyComplaintLabelWidth+3, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(bodyComplaintEntity);
            cont.endText();
            
            bodyC+=10;
            
            //-----------------------------------------------------------------------
            //					BODY DESCRIPTION COMPLAINT
            //-----------------------------------------------------------------------
            String bodyDescComplaintLabel=messageSource.getMessage(DESCRIPTION_COMPLAINT, null, locale);
            String bodyDescComplaintEntity=queja.getDescQueja()!=null?queja.getDescQueja():"";
            String bodyDescComplaint = bodyDescComplaintLabel.concat(bodyDescComplaintEntity);
            System.out.println(bodyDescComplaint);
            List<String>paragraphDescComp=getParagraph(10, fontTimes, mediaBox.getWidth()-50, bodyDescComplaint);
            float bodyDescComplaintLabelWidth = (fontTimes.getStringWidth(bodyDescComplaintLabel) / 1000 * 10);
            for(String p:paragraphDescComp) {
            	
            	// cont.newLineAtOffset(25+contactWidth+3, mediaBox.getHeight()-(headerFolio+100));
            	
            	if(p.contains(bodyDescComplaintLabel)) {
            		String parag=p.replace(bodyDescComplaintLabel, "");
            		
            		cont.beginText();
                    cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=15));
                    System.out.println(bodyC);
                	System.out.println(bodyDescComplaintLabel);
                	cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
                	cont.showText(bodyDescComplaintLabel);
                	cont.endText();
            		
            		cont.beginText();
            		cont.newLineAtOffset(25+bodyDescComplaintLabelWidth+3, mediaBox.getHeight()-(bodyC));
            		System.out.println(parag);
            		cont.setFont(PDType1Font.HELVETICA, 10);
                	cont.showText(parag);
            		cont.endText();
            	}else {
	            	cont.beginText();
	                cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=15));
	                System.out.println(bodyC);
	            	System.out.println(p);
	            	cont.setFont(PDType1Font.HELVETICA, 10);
	            	cont.showText(p);
	            	cont.endText();
            	}
                
            }
            bodyC+=10;
            //-----------------------------------------------------------------------
            //					BODY FOLLOW UP COMPLAINT
            //-----------------------------------------------------------------------
            String bodyFollowUpLabel = messageSource.getMessage(FOLLOW_UP_COMPLAINT, null, locale);
            String bodyFollowUpEntity = queja.getSegQueja()!=null?queja.getSegQueja():"";
            String bodyFollowUp = bodyFollowUpLabel.concat(bodyFollowUpEntity);
            List<String>paragraphResComp=getParagraph(10, fontTimes, mediaBox.getWidth()-50, bodyFollowUp);
            float bodyFollowUpLabelWidth = (fontTimes.getStringWidth(bodyFollowUpLabel) / 1000 * 10);
            
            for(String p:paragraphResComp) {
            	if(p.contains(bodyFollowUpLabel)) {
            		String parag = p.replace(bodyFollowUpLabel, "");
            		
            		cont.beginText();
                    cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=15));
                    System.out.println(bodyC);
                	System.out.println(bodyFollowUpLabel);
                	cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
                	cont.showText(bodyFollowUpLabel);
                	cont.endText();
            		
            		cont.beginText();
            		cont.newLineAtOffset(25+bodyFollowUpLabelWidth+3, mediaBox.getHeight()-(bodyC));
            		System.out.println(parag);
            		cont.setFont(PDType1Font.HELVETICA, 10);
                	cont.showText(parag);
            		cont.endText();
            	}else {
	            	cont.beginText();
	                cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=15));
	                System.out.println(bodyC);
	            	System.out.println(p);
	            	cont.setFont(PDType1Font.HELVETICA, 10);
	            	cont.showText(p);
	            	cont.endText();
            	}
            }
            bodyC+=20;
            //-----------------------------------------------------------------------
            //					BODY RESPONSIBLE COMPLAINT
            //-----------------------------------------------------------------------
            //cont.beginText();
            //cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=20));
            //System.out.println(bodyC);
            String responsibleComplaintLabel = messageSource.getMessage(RESPONSIBLE_COMPLAINT, null, locale);
            float responsibleWidth = (fontTimes.getStringWidth(responsibleComplaintLabel) / 1000 * 10);
            String responsibleComplaintEntity = queja.getResponsableSeg()!=null?queja.getResponsableSeg():"";
            //String responsibleComplaint = responsibleComplaintLabel.concat(responsibleComplaintEntity);
            //cont.showText(responsibleComplaint);
            //cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(bodyC+=20));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(responsibleComplaintLabel);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+responsibleWidth+3, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(responsibleComplaintEntity);
            cont.endText();
            
            bodyC+=10;
            //-----------------------------------------------------------------------
            //							BODY ENGAGEMENT
            //-----------------------------------------------------------------------
            String engagementComplaintLabel = messageSource.getMessage(ENGAGEMENT_COMPLAINT, null, locale);
            String engagementComplaintEntity = queja.getCompromiso()!=null?queja.getCompromiso():"";
            String engagementComplaint = engagementComplaintLabel.concat(engagementComplaintEntity);
            List<String>paragraphEngComp=getParagraph(10, fontTimes, mediaBox.getWidth()-50, engagementComplaint);
            float engagementComplaintLabelWidth = (fontTimes.getStringWidth(engagementComplaintLabel) / 1000 * 10);
            for(String p:paragraphEngComp) {
            	if(p.contains(engagementComplaintLabel)) {
            		String parag=p.replace(engagementComplaintLabel, "");
            		
            		cont.beginText();
                    cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=15));
                    System.out.println(bodyC);
                	System.out.println(engagementComplaintLabel);
                	cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
                	cont.showText(engagementComplaintLabel);
                	cont.endText();
            		
            		cont.beginText();
            		cont.newLineAtOffset(25+engagementComplaintLabelWidth+3, mediaBox.getHeight()-(bodyC));
            		System.out.println(parag);
            		cont.setFont(PDType1Font.HELVETICA, 10);
                	cont.showText(parag);
            		cont.endText();
            	}else {
	            	cont.beginText();
	                cont.newLineAtOffset(25, mediaBox.getHeight() - (bodyC+=15));
	                System.out.println(bodyC);
	            	System.out.println(p);
	            	cont.setFont(PDType1Font.HELVETICA, 10);
	            	cont.showText(p);
	            	cont.endText();
            	}
            }
            //-----------------------------------------------------------------------
            //							BODY DATE COMPLAINT
            //-----------------------------------------------------------------------
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            String dateComplaint=messageSource.getMessage(DATE_COMPLAINT, null, locale);
            //String dCom=queja.getFechaQueja()!=null?queja.getFechaQueja().toString().substring(0, 10):"";
            String dCom=queja.getFechaQueja()!=null?formateador.format(queja.getFechaQueja()):"";
            float dateComplaintWidth = (fontTimes.getStringWidth(dateComplaint) / 1000 * 10);
            
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(bodyC+=40));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(dateComplaint);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+dateComplaintWidth+3, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(dCom);
            cont.endText();
            
            String dateClose=messageSource.getMessage(DATE_CLOSE, null, locale);
            //String dClose=queja.getFechaCierre()!=null?queja.getFechaCierre().toString().substring(0, 10):"";
            String dClose=queja.getFechaCierre()!=null?formateador.format(queja.getFechaCierre()):"";
            float dateCloseWidth = (fontTimes.getStringWidth(dateClose) / 1000 * 10)+3;
            float dCloseWidth = (fontTimes.getStringWidth(dClose) / 1000 * 10);  
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-dateCloseWidth-dCloseWidth, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(dateClose);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-dCloseWidth, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(dClose);
            cont.endText();
            //-----------------------------------------------------------------------
            //							BODY DATE CAPTURE
            //-----------------------------------------------------------------------
            
            String dateCapture=messageSource.getMessage(DATE_CAPTURE, null, locale);
            //String dCap=queja.getFechaCaptura()!=null?queja.getFechaCaptura().toString().substring(0, 10):"";
            String dCap=queja.getFechaCaptura()!=null?formateador.format(queja.getFechaCaptura()):"";
            float dateCaptureWidth = (fontTimes.getStringWidth(dateCapture) / 1000 * 10);
            
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(bodyC+=20));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(dateCapture);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+dateCaptureWidth+3, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(dCap);
            cont.endText();
            
            String audit=messageSource.getMessage(AUDIT, null, locale);
            String auditStatus=queja.getValidoEval()!=null?queja.getValidoEval().equals("Y")?messageSource.getMessage(ACTIVE, null, locale):messageSource.getMessage(INACTIVE, null, locale):"";
            float auditWidth = (fontTimes.getStringWidth(audit) / 1000 * 10)+3;
            float auditStatusWidth = (fontTimes.getStringWidth(auditStatus) / 1000 * 10);  
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-auditWidth-auditStatusWidth, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(audit);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(mediaBox.getWidth()-25-auditStatusWidth, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(auditStatus);
            cont.endText();
            
            //-----------------------------------------------------------------------
            //							BODY REPORT
            //-----------------------------------------------------------------------
            
            String report=messageSource.getMessage(REPORT, null, locale);
            String whoReport=queja.getReporta()!=null?queja.getReporta():"";
            float reportWidth = (fontTimes.getStringWidth(report) / 1000 * 10);
            
            
            cont.beginText();
            cont.newLineAtOffset(25, mediaBox.getHeight()-(bodyC+=20));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA_BOLD, 10);
            cont.showText(report);
            cont.endText();
            
            cont.beginText();
            cont.newLineAtOffset(25+reportWidth+3, mediaBox.getHeight()-(bodyC));
            System.out.println(bodyC);
            cont.setFont(PDType1Font.HELVETICA, 10);
            cont.showText(whoReport);
            cont.endText();
            
            
            //-----------------------------------------------------------------------
            //							FOOTER
            //-----------------------------------------------------------------------
            cont.beginText();
            cont.newLineAtOffset(25, 25);
            System.out.println(bodyC);
            String footer = "Cummins Inc.";
            cont.showText(footer);
            cont.endText();
            
            cont.setNonStrokingColor(Color.RED);
            cont.fillRect(0, 0, mediaBox.getWidth(), 10);
            
            cont.close();
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
       
        ByteArrayOutputStream out =  new ByteArrayOutputStream();
        doc.save(out);
        doc.close();
        logger.info("Pdf created successfully");
        return out;
	}
	
	
	private List<String> getParagraph(int fontSize, PDFont pdfFont, float width, String text){
		List<String> lines = new ArrayList<String>();
		try {
	    
	    int lastSpace = -1;
	    while (text.length() > 0)
	    {
	        int spaceIndex = text.indexOf(' ', lastSpace + 1);
	        if (spaceIndex < 0)
	            spaceIndex = text.length();
	        String subString = text.substring(0, spaceIndex);
	        float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
	        //System.out.printf("'%s' - %f of %f\n", subString, size, width);
	        if (size > width)
	        {
	            if (lastSpace < 0)
	                lastSpace = spaceIndex;
	            subString = text.substring(0, lastSpace);
	            lines.add(subString);
	            text = text.substring(lastSpace).trim();
	            //System.out.printf("'%s' is line\n", subString);
	            lastSpace = -1;
	        }
	        else if (spaceIndex == text.length())
	        {
	            lines.add(text);
	            //System.out.printf("'%s' is line\n", text);
	            text = "";
	        }
	        else
	        {
	            lastSpace = spaceIndex;
	        }
	    }
		}catch(Exception e) {
			logger.error("an error ocurred when trying to create the pdf: "+e.getMessage());
			return null;
		}
		
		
		
		
		return lines;
	}
    
}

