/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mepsan.MlbClean.Core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author hamza.erdal
 */
@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<TokenAuthenticationFilter> tokenAuthenticationFilterRegistration() {
        FilterRegistrationBean<TokenAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenAuthenticationFilter());
        registrationBean.addUrlPatterns("/api/user/*");
        registrationBean.addUrlPatterns("/api/auth/update");
        registrationBean.addUrlPatterns("/api/v2/user/*");
        registrationBean.addUrlPatterns("/api/task/*");
        registrationBean.addUrlPatterns("/api/v2/task/*");
        registrationBean.addUrlPatterns("/api/taskinfo/*");
        registrationBean.addUrlPatterns("/api/v2/taskinfo/*");
        registrationBean.addUrlPatterns("/api/device/*");
        registrationBean.addUrlPatterns("/api/v2/device/*");
        registrationBean.addUrlPatterns("/api/survey/*");
        registrationBean.addUrlPatterns("/api/v2/survey/all");
        registrationBean.addUrlPatterns("/api/v2/survey/{id}");
        registrationBean.addUrlPatterns("/api/v2/survey/device/{id}");
        registrationBean.addUrlPatterns("/api/v2/survey/datebetween");
        registrationBean.addUrlPatterns("/api/v2/survey/rating/{rating}");
        registrationBean.addUrlPatterns("/api/v2/survey/delete/{id}");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Burada tüm originlere izin verildi. Güvenlik nedenleriyle, prodüksiyon ortamında belirli originlere izin vermelisiniz.
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

    }
}
