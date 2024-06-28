package com.example.oms.studentproject;

import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Service.StudentService;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class testSearchByName {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;



    @Test
    public void searchByNameTest()
    {
        Pageable pageable = PageRequest.of(0, 10);


        List<Student> students = Arrays.asList(new Student((long)26,"akash","user_akash","sathe","mundhe",4533993465.0,"sathe@774.com","sathe#mundhe", LocalDateTime.now(),LocalDateTime.now(),false,true)
                ,new Student((long)27,"sankalp","user_sankalp","sathe","mundhe",4533993465.0,"sathe@774.com","sathe#mundhe", LocalDateTime.now(),LocalDateTime.now(),false,true));


        Page<Student> studentPage = new PageImpl<>(students);

        Mockito.when(studentRepository.findByName(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(studentPage);

          Object result = studentService.searchByName("animesh",pageable);

          assertEquals(result,studentPage);


        Mockito.verify(studentRepository, Mockito.times(1)).findByName("animesh",pageable);

    }




}
