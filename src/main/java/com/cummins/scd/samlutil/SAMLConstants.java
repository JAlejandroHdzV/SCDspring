package com.cummins.scd.samlutil; /*Class added for CumminsLATAMCloudMigration */

import java.io.File;

import org.springframework.core.io.ClassPathResource;

/**
 * @file SAMLConstants.java
 * @author ALEJANDRO HDZ-TRANSOM-GROUP.
 * @since 10/06/2021
 */
public final class SAMLConstants {

//	public static final String HTTPS_PROTOCOL_STRING = "https";
//
//	public static final String DES_CONTEXT_PATH = "/DES";
//
//	public static final String DES_APPLICATION_NAME = "DES";
//
//	public static final String DEFAULT_SUCCESS_URL = "/";
//	
//	/* Stage Changes Start
//	public static final String ENTITY_ID = "DESStgSamlEntity";
//	public static final String METADATA_LOCATIONS = "classpath:/DES_STG_ENTAPP_SAML.xml";	
//	public static final String DES_APPLICATION_URL = "https://des-aws-stg.cummins.com/DES";
//	public static final String DES_SERVER_NAME = "des-aws-stg.cummins.com";
//	public static final String KEYSTORE_LOCATION = "classpath:/samlKeystoreStg.jks";	
//	 Stage Changes end*/
//	
//	
//	// Dev Changes Start
//	public static final String ENTITY_ID = "DESSamlEntity";/*REVISAR ESTA RUTA*/
//	public static final String METADATA_LOCATIONS = "classpath:/DES_DEV_ENTAPP_SAML.xml";	
//	public static final String KEYSTORE_LOCATION = "classpath:/samlKeystoreDev.jks";
//	public static final String DES_APPLICATION_URL = "https://des-aws-dev.cummins.com/DES";
//	public static final String DES_SERVER_NAME = "des-aws-dev.cummins.com";
//	//Dev Changes end
//	
//	/* PRD Changes Start
//	public static final String ENTITY_ID = "DESPrdSamlEntity";
//	public static final String METADATA_LOCATIONS = "classpath:/DES_PRD_ENTAPP_SAML.xml";	
//	public static final String KEYSTORE_LOCATION = "classpath:/samlKeystorePrd.jks";
//	public static final String COTIZADOR_APPLICATION_URL = "https://DES-aws.cummins.com/DES";
//	public static final String COTIZADOR_SERVER_NAME = "des-aws.cummins.com";
//	/* PRD Changes end*/
//	
//
//	
//	
//	public static final String KEYSTORE_KEY = "apollo";
//	
//	public static final String LOG_DOWNLOAD_SERVLET_PATH = "/logServlet";
//
//	public static final String KEYSTORE_PASSWORD = "nalle123";	
//	
//	//public static final String SAML_LOGIN_PATH = "/saml/login"; 
//	public static final String SAML_LOGIN_PATH = "/saml/SSO"; 
//
//	public static final String DES_APPLICATION_HOME_URL = "/";
//
//	public static final String ENTITY_ID_URL = "https://globallogin.cummins.com/oam/fed";
//	
//	//public static final String ENTITY_ID_URL = "https://sts.windows.net/b31a5d86-6dda-4457-85e5-c55bbc07923d/";
//	
//	public static final String DES_APPLICATION_LOGOUT_URL = "https://globallogin.cummins.com/OAMlogout/logout.jsp";
//
//	public static final String DES_APPLICATION_AZURE_LOGOUT_URL = "https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0";
	/*public static final String metadataLocations="", keystoreLocation="";
	
	try {
	metadataLocations = new ClassPathResource("DES_DEV_ENTAPP_SAML.xml").getPath();
	keystoreLocation = new ClassPathResource("samlKeystoreDev.jks").getPath();
	}catch(Exception e) {
		System.out.println("Metadata Locations");
	}*/
	
	public static final String HTTPS_PROTOCOL_STRING = "https";

	public static final String DES_CONTEXT_PATH = "/DES";

	public static final String DES_APPLICATION_NAME = "DES_ADMIN";

	public static final String DEFAULT_SUCCESS_URL = "/Home";
	
	/* Stage Changes Start
	public static final String ENTITY_ID = "DESStgSamlEntity";
	public static final String METADATA_LOCATIONS = "classpath:/DES_STG_ENTAPP_SAML.xml";	
	public static final String APPLICATION_URL = "https://des-aws-stg.cummins.com/DES";
	public static final String SERVER_NAME = "des-aws-stg.cummins.com";
	public static final String KEYSTORE_LOCATION = "classpath:/samlKeystoreStg.jks";	
	Stage Changes end*/
	
	
	/* Dev Changes Start*/
	public static final String ENTITY_ID = "DESSamlEntity";
	public static final String METADATA_LOCATIONS = "classpath:/DES_DEV_ENTAPP_SAML.xml";	
	public static final String KEYSTORE_LOCATION = "classpath:/samlKeystoreDev.jks";
	public static final String APPLICATION_URL = "https://des-aws-dev.cummins.com/DES";
	public static final String SERVER_NAME = "des-aws-dev.cummins.com";
	/* Dev Changes end*/
	
	/* Prod Changes Start
	public static final String ENTITY_ID = "DESPrdSamlEntity";
	public static final String METADATA_LOCATIONS = "classpath:/DES_PRD_ENTAPP_SAML.xml";	
	public static final String APPLICATION_URL = "https://des-aws.cummins.com/DES";
	public static final String SERVER_NAME = "des-aws.cummins.com";
	public static final String KEYSTORE_LOCATION = "classpath:/samlKeystorePrd.jks";	
	/* Prod Changes end*/
	
	public static final String KEYSTORE_KEY = "apollo";
	
	public static final String LOG_DOWNLOAD_SERVLET_PATH = "/download/log";

	public static final String KEYSTORE_PASSWORD = "nalle123";	
	
	public static final String SAML_LOGIN_PATH = "/saml/login"; 

	public static final String APPLICATION_HOME_URL = "/DES";

	public static final String ENTITY_ID_URL = "https://globallogin.cummins.com/oam/fed";
	
	//public static final String ENTITY_ID_URL = "https://sts.windows.net/b31a5d86-6dda-4457-85e5-c55bbc07923d/";
	
	public static final String APPLICATION_LOGOUT_URL = "https://globallogin.cummins.com/OAMlogout/logout.jsp";

	public static final String APPLICATION_AZURE_LOGOUT_URL = "https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0";

}
