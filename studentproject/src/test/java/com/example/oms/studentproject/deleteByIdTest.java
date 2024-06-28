package com.example.oms.studentproject;

import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class deleteByIdTest {

   @Mock
    StudentRepository studentRepository;

   @InjectMocks
    StudentServiceImpl studentService;

   @Test
   public void testDeleteById()
   {

       //when(studentRepository.existsById((long)500)).thenReturn(true);
       when(studentRepository.existsById((long)500)).thenReturn(false);

       Object result = studentService.deleteById((long) 500);



      // assertEquals("student deleted succesfully",result);
       assertEquals("student not found",result);

   }





}
