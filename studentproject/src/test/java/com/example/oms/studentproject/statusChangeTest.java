package com.example.oms.studentproject;


import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class statusChangeTest {

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
    public void testStatusChange_ActiveToInactive() throws Exception {
        when(studentRepository.existsById((long)500)).thenReturn(true);

        when(studentRepository.findById((long)500)).thenReturn(Optional.of(student));

       // doNothing().when(studentRepository).save(student);
        //use doNothing() for mocking only those methods who have return type void


        Object result = studentService.statusChange((long) 500);

        assertEquals("student is not active",result);
        Assertions.assertFalse(student.getIsActive());
        //we have asserted false here because once our status change method is called the status is updated to false
        // so, in order to set method to behave in expected way we have asserted this to false
        Mockito.verify(studentRepository, Mockito.times(1)).existsById((long) 500);
        Mockito.verify(studentRepository, Mockito.times(1)).findById((long) 500);
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);


    }

    @Test
    public void testStatusChange_InactiveToActive() throws Exception {

        Student student = new Student();
        student.setIsActive(false);


        when(studentRepository.existsById((long)500)).thenReturn(true);

        when(studentRepository.findById((long)500)).thenReturn(Optional.of(student));


        Object result1 = studentService.statusChange((long) 500);

        assertEquals("student is active",result1);

        Assertions.assertTrue(student.getIsActive());

        Mockito.verify(studentRepository, Mockito.times(1)).existsById((long) 500);
        Mockito.verify(studentRepository, Mockito.times(1)).findById((long) 500);
       // Mockito.verify(studentRepository, Mockito.times(1)).save(student);

    }

    @Test
    public void testStatusChange_StudentNotFound() throws Exception {

        when(studentRepository.existsById((long)500)).thenReturn(false);
       // when(studentRepository.findById((long)500)).thenReturn(Optional.of(student));


        // Use assertThrows to verify that the exception is thrown
        Exception exception = assertThrows(Exception.class, () -> {
            studentService.statusChange((long) 500);
        });
        // Serviceimpl mdhe jar existById() hi method false zali tr student not found he
        // Exception throw kel jate te handle krnyasathi vrchi method assertThrows mdhe call keli
        //Exception exception here, we declared variable exception of type Exception
        //assertThrows means we are expecting an Exception to be thrown. there are two arguments
        //Exception.class it mean the exception can be any sub class of class Exception
        // in that lambda expression we have method from which we are expecting an Exception may be thrown

        assertEquals("student not found",exception.getMessage());

        Mockito.verify(studentRepository, Mockito.times(1)).existsById((long) 500);
       // Mockito.verify(studentRepository, Mockito.times(1)).findById((long) 500);
       // Mockito.verify(studentRepository, Mockito.times(1)).save(student);



    }


}
