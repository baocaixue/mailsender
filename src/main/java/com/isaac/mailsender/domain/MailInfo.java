package com.isaac.mailsender.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MailInfo {
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String content;
}
