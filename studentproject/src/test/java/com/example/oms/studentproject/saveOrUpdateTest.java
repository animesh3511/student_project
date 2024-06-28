package com.example.oms.studentproject;


import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Model.request.StudentRequest;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class saveOrUpdateTest {

    @Mock
    private StudentRepository studentRepository;


    @InjectMocks
    private StudentServiceImpl studentService;


    private Student student;

    @BeforeEach
    void setUp()
    {

        student = new Student();
        student.setStudentId((long) 17);
        student.setName("rishikesh");
        student.setUserName("user_rishikesh");
        student.setLastName("patil");         // ha object ethe create kely bcoz jeva
                                              //   Mockito.when(studentRepository.findById((long) 33)).thenReturn(Optional.of(student));
                                             //hi findById() method call hoel teva aplyala ek student cha object
        student.setEmail("patil@example.com");//return karycha ahe mhnun ha object construct kela ahe so,
        student.setPassword("patil#pwd");    // Mockito.when().thenReturn() mdhe to return hoel
        student.setLocation("tambave");
        student.setContact(8033725465.0);
        student.setIsActive(true);
        student.setIsDeleted(false);



    }



    @Test
    public void testSaveOrUpdate() throws Exception {
        StudentRequest studentRequest = new StudentRequest(); //for update
        studentRequest.setStudentId((long) 33);
        studentRequest.setName("vishwajeet");
        studentRequest.setLastName("kadam");
        studentRequest.setLocation("kadegaon");
        studentRequest.setContact(7865748586.0);
        studentRequest.setEmail("kadam@gmail.com");
        studentRequest.setPassword("kadam#3344");
        studentRequest.setUserName("user_vishwajeet");

      //  Mockito.when(studentRepository.existsById((long) 33)).thenReturn(false);
        Mockito.when(studentRepository.existsById((long) 33)).thenReturn(true);
        Mockito.when(studentRepository.findById((long) 33)).thenReturn(Optional.of(student));




       // Object result = studentService.SaveOrUpdate(studentRequest);
        Object result1 = studentService.SaveOrUpdate(studentRequest);

        Mockito.verify(studentRepository, Mockito.times(1)).save(Mockito.any(Student.class));

       // assertEquals("student saved", result);
        assertEquals("student updated", result1);



    }

}
