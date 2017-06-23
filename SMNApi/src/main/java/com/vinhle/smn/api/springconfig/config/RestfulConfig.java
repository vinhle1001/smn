package com.vinhle.smn.api.springconfig.config;

import com.vinhle.smn.api.springconfig.filter.RequestFilter;
import com.vinhle.smn.api.springconfig.filter.ResponseFilter;
import com.vinhle.smn.api.springconfig.resolver.JsonArgumentResolver;
import com.vinhle.smn.api.springconfig.resolver.JsonBodyArgumentResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

public class RestfulConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean requestFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.addUrlPatterns();
        registrationBean.setFilter(new RequestFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean responseFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.addUrlPatterns();
        registrationBean.setFilter(new ResponseFilter());
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
        argumentResolvers.add(new JsonBodyArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}
