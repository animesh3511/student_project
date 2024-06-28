package com.example.oms.studentproject;


import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllRecordsTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    @Test
    public void testGetAllRecords()
    {

        List<Student> students = Arrays.asList(new Student((long)26,"akash","user_akash","sathe","mundhe",4533993465.0,"sathe@774.com","sathe#mundhe", LocalDateTime.now(),LocalDateTime.now(),false,true)
                ,new Student((long)27,"sankalp","user_sankalp","sathe","mundhe",4533993465.0,"sathe@774.com","sathe#mundhe", LocalDateTime.now(),LocalDateTime.now(),false,true));


        Mockito.when(studentRepository.findAll()).thenReturn(students);


        Object result = studentService.getAllRecords();

        assertNotNull(result);

        assertEquals(students,result);



    }

}
