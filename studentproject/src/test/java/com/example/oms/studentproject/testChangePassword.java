package com.example.oms.studentproject;


import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class testChangePassword {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setUp()
    {

        student = new Student();
        student.setStudentId((long) 17);
        student.setName("rishikesh");
        student.setUserName("user_rishikesh");
        student.setLastName("patil");
        student.setEmail("patil@example.com");
        student.setPassword("patil#pwd");
        student.setLocation("tambave");
        student.setContact(8033725465.0);
        student.setIsActive(true);
        student.setIsDeleted(false);

    }

  @Test
  public void testChangePasswordSuccess()
  {

      when(studentRepository.findById((long)500)).thenReturn(Optional.of(student));

     Object result = studentService.changePassword((long) 500,"patil#pwd","newPassword");

      Assertions.assertEquals("student saved", result);

      Assertions.assertEquals("newPassword", student.getPassword());



  }

  @Test
  public  void testChangePasswordIncorrectPassword()
  {


      when(studentRepository.findById((long)500)).thenReturn(Optional.of(student));

      Object result = studentService.changePassword((long) 500,"incorrectPassword","newPassword");

      Assertions.assertEquals("oldPassword is incorrect", result);


  }

    @Test
    public void testChangePasswordIncorrectStudentId()
  {

      Object result = studentService.changePassword((long) 0,"patil#pwd","newPassword");

      Assertions.assertEquals("studentId is incorrect", result);


  }


}
