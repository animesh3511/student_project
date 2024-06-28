package com.example.oms.studentproject.Serviceimpl.Factory;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MimeMessageHelperFactory {

    public MimeMessageHelper createMimeMessageHelper(MimeMessage mimeMessage, boolean multipart) {
        try {
            return new MimeMessageHelper(mimeMessage, multipart);
        } catch (MessagingException e) {
            // Handle exception or rethrow as appropriate
            return null;
        }
    }



}
