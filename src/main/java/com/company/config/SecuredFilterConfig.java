package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> filterRegistrationBeanRegion() {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<JwtFilter>();
        bean.setFilter(jwtFilter);
        bean.addUrlPatterns("/profile/*");
        bean.addUrlPatterns("/product/seller/*");
        bean.addUrlPatterns("/types/admin/*");
        bean.addUrlPatterns("/category/admin/*");
        bean.addUrlPatterns("/color/admin/*");
        bean.addUrlPatterns("/brand/admin/*");
        bean.addUrlPatterns("/attach/admin/*");

        return bean;
    }

}
