package com.isaac.mailsender.service;

import com.isaac.mailsender.util.FileUtil;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Service
public class MailTemplateService {
    private final Configuration fmtConfiguration;
    @Value("${mail-sender.templates-folder}")
    private String templatePath;

    public MailTemplateService(Configuration fmtConfiguration) {
        this.fmtConfiguration = fmtConfiguration;
    }

    public String compileTemplate(String templateName, Map<String, Object> mailData) throws Exception{
        fmtConfiguration.setDirectoryForTemplateLoading(FileUtil.getFileFromPathString(templatePath));
        var template = fmtConfiguration.getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, mailData);
    }
}
