package com.example.oms.studentproject.Serviceimpl;

import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByStudentId(Long studentId) throws UsernameNotFoundException {

              Student student = studentRepository.findById(studentId)
                      .orElseThrow(()-> new UsernameNotFoundException("Student not found with Id: "+studentId));

             return UserDetailsImpl.build(student);


    }
}
