package com.isaac.mailsender.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class MailInfo {
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String content;
    private String[] attachmentPaths;

    private String templateName;
    private Map<String, Object> templateData;
}
