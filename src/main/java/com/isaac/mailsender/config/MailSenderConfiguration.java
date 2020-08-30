package com.isaac.mailsender.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MailSenderConfiguration {
    @Autowired
    private MessageFilePathProperties messageFilePathProperties;

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        var source = new ReloadableResourceBundleMessageSource();
        source.setBasename(messageFilePathProperties.getPath());
        source.setDefaultEncoding(messageFilePathProperties.getEncoding());
        source.setUseCodeAsDefaultMessage(messageFilePathProperties.isCodeAsDefaultMessage());
        source.setCacheSeconds(messageFilePathProperties.getCacheSeconds());
        return source;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
