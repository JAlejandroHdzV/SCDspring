package com.cummins.scd;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LangConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {
	//-------------------------------------------------------------------------
    //		BEAN TO CONFIGURE LOCALE RESOLVER FOR STANDAR LANGUAGE I18N
    //-------------------------------------------------------------------------
    @Bean
    public LocaleResolver localeResolver() {
    	SessionLocaleResolver localeResolver= new SessionLocaleResolver();
    	localeResolver.setDefaultLocale(new Locale("es", "ES"));
    	return localeResolver;
    }
    
    //-------------------------------------------------------------------------
    //			BEAN TO CONFIGURE INTERCEPTOR FOR CHANGE LANGUAGE
    //-------------------------------------------------------------------------
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
    	LocaleChangeInterceptor localeChangeInterceptor= new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("lang");
    	//System.out.println("interceptor lang");
    	return localeChangeInterceptor;
    	
    }
    //-------------------------------------------------------------------------
    //						REGISTERING INTERCEPTOR
    //-------------------------------------------------------------------------
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(localeChangeInterceptor());
	}
    

    

}
