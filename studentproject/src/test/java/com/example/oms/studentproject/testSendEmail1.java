package com.example.oms.studentproject;

import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
public class testSendEmail1 {

    @Mock
    JavaMailSender javaMailSender;

    @InjectMocks
    StudentServiceImpl studentService;

    @Test
    public void testSendEmail1()
    {
        String to = "example@example.com";
        String cc = "cc@example.com";
        String subject = "Test Subject";
        String body = "Test Body";


        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setFrom(studentService.getFromEmail());
        expectedMessage.setTo(to);
      //  expectedMessage.setCc(cc);
        expectedMessage.setSubject(subject);
        expectedMessage.setText(body);

        studentService.sendEmail1(to,cc, subject, body);
        //studentService.sendEmail1(to,cc, subject, body);

        Mockito.verify(javaMailSender, Mockito.times(1)).send(expectedMessage);




    }


}
