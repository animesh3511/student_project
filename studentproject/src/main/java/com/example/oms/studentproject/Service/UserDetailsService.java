package com.example.oms.studentproject.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {

    UserDetails loadUserByStudentId(Long studentId) throws UsernameNotFoundException;



}
