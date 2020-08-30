package com.isaac.mailsender.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "messages.i18n")
public class MessageFilePathProperties {
    private String path;

    private String encoding = "UTF-8";

    private int cacheSeconds = 10;

    private boolean codeAsDefaultMessage = true;
}
