//package com.cummins.scd;
//
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import com.cummins.devops.security.XSSFilter;
//import com.cummins.scd.filters.ValidateUserAccess;
//
////@Configuration
//public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
//
//    @Autowired
//    private ValidateUserAccess userValidationFilter;
//
//    //@Bean
//    public FilterRegistrationBean xssFilter(){
//        FilterRegistrationBean regBean = new FilterRegistrationBean();
//        regBean.setFilter(new XSSFilter());
//        regBean.addUrlPatterns("/*");
//        regBean.addInitParameter("xssfilter-debug-enable", "false");
//        regBean.setOrder(1);
//       
//        return regBean;
//    }
//
//   // @Bean
//    public FilterRegistrationBean userValidatorFilter() {
//    	FilterRegistrationBean regBean = new FilterRegistrationBean();
//    	
//             regBean.setFilter(userValidationFilter);
//             regBean.setOrder(2);
//             regBean.addUrlPatterns("/*");
//
//             
//        	
//    	return regBean;
//    }
//    
//  
//    
//    
//}