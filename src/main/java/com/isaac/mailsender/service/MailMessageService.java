package com.isaac.mailsender.service;

import com.isaac.mailsender.domain.MailInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
@AllArgsConstructor
public class MailMessageService {
    private JavaMailSender javaMailSender;

    public void sendMail(MailInfo mailInfo) throws MessagingException {
        log.info(mailInfo.toString());
        var mail = javaMailSender.createMimeMessage();
        var mailHelper = new MimeMessageHelper(mail, false);
        mailHelper.setFrom(mailInfo.getFrom());
        mailHelper.setTo(mailInfo.getTo());
        mailHelper.setSubject(mailInfo.getSubject());
        mailHelper.setText(mailInfo.getContent());
        javaMailSender.send(mail);
    }
}
