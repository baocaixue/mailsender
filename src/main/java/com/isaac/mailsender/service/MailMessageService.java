package com.isaac.mailsender.service;

import com.isaac.mailsender.domain.MailInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class MailMessageService {
    private JavaMailSender javaMailSender;
    private MailTemplateService mailTemplateService;


    public void sendMail(MailInfo mailInfo) throws Exception {
        var mail = javaMailSender.createMimeMessage();
        var mailHelper = new MimeMessageHelper(mail, false);
        String[] attachmentPaths = mailInfo.getAttachmentPaths();
        if (Objects.nonNull(attachmentPaths)) {
           mailHelper = new MimeMessageHelper(mail, true);
            for (String attachmentPath : attachmentPaths) {
                var file = new FileSystemResource(new File(attachmentPath));
                mailHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
        }
        mailHelper.setFrom(mailInfo.getFrom());
        mailHelper.setTo(mailInfo.getTo());
        mailHelper.setCc(mailInfo.getCc());
        mailHelper.setSubject(mailInfo.getSubject());

        var content = mailInfo.getContent();
        if (StringUtils.isEmpty(content)) {
            content = mailTemplateService.compileTemplate(mailInfo.getTemplateName() + ".ftl", mailInfo.getTemplateData());
            mailHelper.setText(content, true);
        } else {
           mailHelper.setText(content);
        }
        javaMailSender.send(mail);
    }
}
