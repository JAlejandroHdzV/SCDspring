package com.cummins.scd.models.services.catalogs;

import java.util.Locale;

import javax.activation.DataSource;

import org.springframework.context.MessageSource;

public interface IEmailSenderService {
	
	String sendSimpleMail(String[] mailTo, String[] copyTo, String lang, String idQueja, String spCode,
			DataSource dataSource, Locale locale, MessageSource messageSource) throws Exception;

}
