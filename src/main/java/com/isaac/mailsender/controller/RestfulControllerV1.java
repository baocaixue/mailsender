package com.isaac.mailsender.controller;

import com.isaac.mailsender.domain.MailInfo;
import com.isaac.mailsender.service.MailMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestfulControllerV1 {
    private MailMessageService mailMessageService;

    @GetMapping(value = "/health")
    public String dummyHealth() {
        return "{\"result\": \"ok\"}";
    }

    @PostMapping(value = "/mail")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleMailProcess(@RequestBody MailInfo jsonString) throws MessagingException {
        mailMessageService.sendMail(jsonString);
    }
}
