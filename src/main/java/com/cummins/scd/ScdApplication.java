package com.cummins.scd;

import java.util.List;

//import org.opensaml.xml.security.BasicSecurityConfiguration;
//import org.opensaml.xml.signature.SignatureConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.saml.SAMLBootstrap;
//import org.springframework.security.saml.SAMLCredential;
//import org.springframework.security.saml.context.SAMLContextProviderImpl;
//import org.springframework.security.saml.storage.EmptyStorageFactory;
//import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
//import org.springframework.security.saml.websso.WebSSOProfileConsumerImpl;
//import org.springframework.security.saml.websso.WebSSOProfileOptions;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cummins.scd.filters.ValidateUserAccess;

//import com.cummins.scd.ScdApplicationCloud.MyServiceProviderConfig;
//import com.github.ulisesbocchio.spring.boot.security.saml.annotation.EnableSAMLSSO;
//import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderBuilder;
//import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderConfigurerAdapter;
//import com.github.ulisesbocchio.spring.boot.security.saml.user.SAMLUserDetails;

//import com.cummins.scd.filters.ValidateUserAccess;


@SpringBootApplication()
//@EnableSAMLSSO /*Added for Transom Group*/
//@EnableWebSecurity /*Added for Transom Group*/
public class ScdApplication {

	@Autowired
	private ValidateUserAccess userValidationFilter;
	
	public static void main(String[] args) {
		SpringApplication.run(ScdApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean userValidatorFilter() {
        FilterRegistrationBean regBean = new FilterRegistrationBean();
        regBean.setFilter(userValidationFilter);
        regBean.setOrder(2);
        regBean.addUrlPatterns("/*");

        return regBean;
    }
	
	  //@Bean
	    /*public FilterRegistrationBean userValidatorFilter() {
	        FilterRegistrationBean regBean = new FilterRegistrationBean();
	        regBean.setFilter(userValidationFilter);
	        regBean.setOrder(2);
	        regBean.addUrlPatterns("/*");

	        return regBean;
	    }*/
	/*
	 * -----------------------------------------------------------------------------------------------------
	 * ESTA CONFIGURACION SE HABILITARA CUANDO LA AUTENTICACION SSO QUEDE LISTA
	 * -----------------------------------------------------------------------------------------------------
	 * */
	
	/*@Configuration
	public static class MvcConfig implements WebMvcConfigurer {
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/Home");
			registry.addViewController("/protected").setViewName("protected");
			registry.addViewController("/unprotected/help").setViewName("help");
		}

		@Override
		public void configurePathMatch(PathMatchConfigurer configurer) {
		}

		@Override
		public void configureContentNegotiation(
				ContentNegotiationConfigurer configurer) {
		}

		@Override
		public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		}

		@Override
		public void configureDefaultServletHandling(
				DefaultServletHandlerConfigurer configurer) {
		}

		@Override
		public void addFormatters(FormatterRegistry registry) {

		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {
		}

		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
		}

		@Override
		public void addArgumentResolvers(
				List<HandlerMethodArgumentResolver> argumentResolvers) {
		}

		@Override
		public void addReturnValueHandlers(
				List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		}

		@Override
		public void configureMessageConverters(
				List<HttpMessageConverter<?>> converters) {
		}

		@Override
		public void extendMessageConverters(
				List<HttpMessageConverter<?>> converters) {
		}

		@Override
		public void configureHandlerExceptionResolvers(
				List<HandlerExceptionResolver> exceptionResolvers) {
		}

		@Override
		public void extendHandlerExceptionResolvers(
				List<HandlerExceptionResolver> exceptionResolvers) {
		}

		@Override
		public Validator getValidator() {
			return null;
		}

		@Override
		public MessageCodesResolver getMessageCodesResolver() {
			return null;
		}
	}*/
	
	/*
	 * -----------------------------------------------------------------------------------------------------
	 * ESTA CONFIGURACION SE HABILITARA CUANDO LA AUTENTICACION SSO QUEDE LISTA
	 * -----------------------------------------------------------------------------------------------------
	 * */
	
	/*
	@Configuration
	public static class MyServiceProviderConfig extends
			ServiceProviderConfigurerAdapter {
		private static final  Logger log = LoggerFactory.getLogger(MyServiceProviderConfig.class);
		
		@Autowired
		SAMLUserDetailsService userDetailsService;
		
		@Override
		public void configure(ServiceProviderBuilder serviceProvider)
				throws Exception {
			log.info("Entering into SAML configure method");
			SAMLContextProviderImpl contextProvider1 = new SAMLContextProviderImpl();
			contextProvider1.setStorageFactory(new EmptyStorageFactory());

			WebSSOProfileOptions webSSOProfile = new WebSSOProfileOptions();
			webSSOProfile.setIncludeScoping(false);

			WebSSOProfileConsumerImpl consumerImpl = new WebSSOProfileConsumerImpl();
			consumerImpl.setMaxAuthenticationAge(5184000);
			
			serviceProvider
					.ssoProfileConsumer(consumerImpl)
					.metadataGenerator()
					.entityId(com.cummins.scd.samlutil.SAMLConstants.ENTITY_ID)
					.entityBaseURL(com.cummins.scd.samlutil.SAMLConstants.APPLICATION_URL)
					.bindingsSSO("post") 
			.and()
					.sso()
					.profileOptions(webSSOProfile)
					.defaultSuccessURL(com.cummins.scd.samlutil.SAMLConstants.DEFAULT_SUCCESS_URL)
					.defaultFailureURL(com.cummins.scd.samlutil.SAMLConstants.APPLICATION_AZURE_LOGOUT_URL)
			.and()
					.logout()
					.defaultTargetURL(com.cummins.scd.samlutil.SAMLConstants.APPLICATION_LOGOUT_URL)
			.and()
					.metadataManager()
					.metadataLocations(com.cummins.scd.samlutil.SAMLConstants.METADATA_LOCATIONS)
					.refreshCheckInterval(0)
			.and()
					.extendedMetadata()
					.idpDiscoveryEnabled(false)
			.and()
					.keyManager()
					.storeLocation(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_LOCATION)
					.storePass(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_PASSWORD)
					.defaultKey(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_KEY)
					.keyPassword(com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_KEY,com.cummins.scd.samlutil.SAMLConstants.KEYSTORE_PASSWORD)
			.and()
					.authenticationProvider()
			.and()
					.samlContextProviderLb()
					.scheme(com.cummins.scd.samlutil.SAMLConstants.HTTPS_PROTOCOL_STRING)
					.contextPath(com.cummins.scd.samlutil.SAMLConstants.DES_CONTEXT_PATH)
					.serverName(com.cummins.scd.samlutil.SAMLConstants.SERVER_NAME)
					.serverPort(443)
					.includeServerPortInRequestURL(false);

		}
		
		
		@Bean
		public SAMLUserDetailsService userDetailsService() {
		return new SAMLUserDetailsService() {
		@Override
		public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
		    return new SAMLUserDetails(credential);
		}
		};
		}
		
		@Bean
		public static SAMLBootstrap SAMLBootstrap() {
			return new CustomSAMLBootstrap();
		}
		
		static class CustomSAMLBootstrap extends SAMLBootstrap {
			@Override
			public void postProcessBeanFactory(
					ConfigurableListableBeanFactory beanFactory)
					throws BeansException {
				super.postProcessBeanFactory(beanFactory);
				BasicSecurityConfiguration config = (BasicSecurityConfiguration) org.opensaml.Configuration
						.getGlobalSecurityConfiguration();
				config.registerSignatureAlgorithmURI("RSA",
						SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
				config.setSignatureReferenceDigestMethod(SignatureConstants.ALGO_ID_DIGEST_SHA256);
			}
		}
	}*/

}
