package com.example.oms.studentproject.mapper;

import com.example.oms.studentproject.Model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentViewMapper {


public StudentView toStudentView(Student student)
{

    StudentView studentView = new StudentView();

    studentView.setStudentId(student.getStudentId());

    studentView.setActive(student.getIsActive());

    studentView.setContact(student.getContact());

    studentView.setCreatedAt(student.getStudentCreatedAt());

    studentView.setDeleted(student.getIsDeleted());

    studentView.setEmail(student.getEmail());

    studentView.setLastName(student.getLastName());

    studentView.setLocation(student.getLocation());

    studentView.setPassword(student.getPassword());

    studentView.setName(student.getName());

    studentView.setUsername(student.getUserName());

    studentView.setUpdatedAt(student.getStudentUpdatedAt());


  return  studentView;
}


}
