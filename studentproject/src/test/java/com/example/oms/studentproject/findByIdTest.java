package com.example.oms.studentproject;

import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class findByIdTest {

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
       student.setLastName("patil");
       student.setEmail("patil@example.com");
       student.setPassword("patil#pwd");
       student.setLocation("tambave");
       student.setContact(8033725465.0);
       student.setIsActive(true);
       student.setIsDeleted(false);



     }



     @Test
     public void testFindById() throws Exception {


         when(studentRepository.existsById((long)500)).thenReturn(true);

          when(studentRepository.findById((long)500)).thenReturn(Optional.of(student));



          Object result =  studentService.findById((long)500);
          //ethun findbyId() hya method la call jato ji service class mdhe ahe ha call gelyavar
         //pudhe existbyId() and findById hya method na call jato to gelyavar ky krych he aapn
         //vrti when().thenReturn() hya Mockito chya method mdhe set kel ahe

          // here aapn 17 hi argument pathavli ahe bcoz varti existById() and findById()
         //hya donhi method la aapn 17 he argument pathavli ahe so tinhi thikani id chi value same taak


          assertNotNull(result);

          assertEquals(student, result);


     }

}
