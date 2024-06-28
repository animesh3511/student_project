package com.example.oms.studentproject;

import com.example.oms.studentproject.Serviceimpl.Factory.MimeMessageHelperFactory;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.FileSystemResource;
import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.whenNew;
import static org.mockito.ArgumentMatchers.any;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import static org.mockito.ArgumentMatchers.eq;


import org.springframework.core.io.FileSystemResource;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@ExtendWith(MockitoExtension.class)
public class testSendEmailWithAttachment {

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    MimeMessage mimeMessage;

    @Mock
    MimeMessageHelper mimeMessageHelper;

    @Mock
    MimeMessageHelperFactory helperFactory;


    @InjectMocks
    StudentServiceImpl studentService;

    @Test
    public void testSendEmailWithAttachment() throws MessagingException {



        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        when(helperFactory.createMimeMessageHelper(mimeMessage,true)).thenReturn(mimeMessageHelper);


        studentService.sendEmailWithAttachment("to@example.com", "cc@example.com", "Test Subject", "Test Body", "path/to/attachment");

        //  whenNew(MimeMessageHelper.class).withArguments(eq(mimeMessage), eq(true)).thenReturn(mimeMessageHelper);

      //  Mockito.doNothing().when(javaMailSender).send(Mockito.any(MimeMessage.class));
       // when(javaMailSender.createMimeMessageHelper(any(MimeMessage.class), any(Boolean.class)))
       //         .thenReturn(mimeMessageHelper);


        MimeMessage sentMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper expectedMessageHelper = new MimeMessageHelper(sentMessage, true);
        expectedMessageHelper.setFrom("animeshsurya1.omsoftware@gmail.com");
        expectedMessageHelper.setTo("to@example.com");
        expectedMessageHelper.setCc("cc@example.com");
        expectedMessageHelper.setSubject("Test Subject");
        expectedMessageHelper.setText("Test Body");
        expectedMessageHelper.addAttachment(new FileSystemResource("path/to/attachment").getFilename(),new FileSystemResource(new File("path/to/attachment")));



        Mockito.verify(javaMailSender, Mockito.times(1)).send(sentMessage);



    }



}
