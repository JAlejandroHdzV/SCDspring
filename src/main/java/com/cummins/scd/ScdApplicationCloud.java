//package com.cummins.scd;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.saml.SAMLBootstrap;
//import org.springframework.security.saml.SAMLCredential;
//import org.springframework.security.saml.context.SAMLContextProviderImpl;
//import org.springframework.security.saml.storage.EmptyStorageFactory;
//import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
//import org.springframework.security.saml.websso.WebSSOProfileConsumerImpl;
//import org.springframework.security.saml.websso.WebSSOProfileOptions;
//
//import com.github.ulisesbocchio.spring.boot.security.saml.annotation.EnableSAMLSSO;
//import com.github.ulisesbocchio.spring.boot.security.saml.bean.SAMLConfigurerBean;
//import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderBuilder;
//import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderConfigurerAdapter;
//import com.github.ulisesbocchio.spring.boot.security.saml.user.SAMLUserDetails;
//
//public class ScdApplicationCloud {
//
//	//@Configuration
//	public static class MyServiceProviderConfig extends
//			ServiceProviderConfigurerAdapter {
//		private static final  Logger log = LoggerFactory.getLogger(MyServiceProviderConfig.class);
//		
//		@Autowired
//		SAMLUserDetailsService userDetailsService;
//		
//		@Override
//		public void configure(ServiceProviderBuilder serviceProvider)
//				throws Exception {
//			log.info("Entering into SAML configure method");
//			SAMLContextProviderImpl contextProvider1 = new SAMLContextProviderImpl();
//			contextProvider1.setStorageFactory(new EmptyStorageFactory());
//
//			WebSSOProfileOptions webSSOProfile = new WebSSOProfileOptions();
//			webSSOProfile.setIncludeScoping(false);
//
//			WebSSOProfileConsumerImpl consumerImpl = new WebSSOProfileConsumerImpl();
//			consumerImpl.setMaxAuthenticationAge(5184000);
//			
//			serviceProvider
//					.ssoProfileConsumer(consumerImpl)
//					.metadataGenerator()
//					.entityId(com.cummins.scd.samlutil.SAMLConstants.ENTITY_ID)
//					.entityBaseURL(com.cummins.scd.samlutil.SAMLConstants.APPLICATION_URL)
//					.bindingsSSO("post") 
//			.and()
//					.sso()
//					.profileOptions(webSSOProfile)
//					.defaultSuccessURL(com.cummins.scd.samlutil.SAMLConstants.DEFAULT_SUCCESS_URL)
//					.defaultFailureURL(com.cummins.scd.samlutil.SAMLConstants.APPLICATION_AZURE_LOGOUT_URL)
//			.and()
//					.logout()
//					.defaultTargetURL(com.cummins.scd.samlutil.SAMLConstants.APPLICATION_LOGOUT_URL)
//			.and()
//					.metadataManager()
//					.metadataLocations(com.cummins.scd.samlutil.SAMLConstants.METADATA_LOCATIONS)
//					.refreshCheckInterval(0)
//			.and()
//					.extendedMetadata()
//					.idpDiscoveryEnabled(false)
//			.and()
//					.keyManager()
//					.storeLocation(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_LOCATION)
//					.storePass(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_PASSWORD)
//					.defaultKey(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_KEY)
//					.keyPassword(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_KEY,com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_PASSWORD)
//			.and()
//					.authenticationProvider()
//			.and()
//					.samlContextProviderLb()
//					.scheme(com.cummins.scd.samlutil.SAMLConstants.HTTPS_PROTOCOL_STRING)
//					.contextPath(com.cummins.scd.samlutil.SAMLConstants.DES_CONTEXT_PATH)
//					.serverName(com.cummins.scd.samlutil.SAMLConstants.SERVER_NAME)
//					.serverPort(443)
//					.includeServerPortInRequestURL(false);
//
//		}
//		
//		
//		//@Bean
//		public SAMLUserDetailsService userDetailsService() {
//		return new SAMLUserDetailsService() {
//		@Override
//		public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
//		    return new SAMLUserDetails(credential);
//		}
//		};
//		}
//		
//		
//		
//		
//		
//		
//
//		
//		
//		
//		
//		
//		
//	}
//}
