package com.example.library.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SecurityConfig {
@Bean
public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter(ApiKeyFilter filter) {
FilterRegistrationBean<ApiKeyFilter> bean = new FilterRegistrationBean<>();
bean.setFilter(filter);
bean.addUrlPatterns("/*");
bean.setOrder(1);
return bean;
}
}
