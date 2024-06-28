package com.example.oms.studentproject.Serviceimpl;

import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Service.UserDetailsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {


    private final Student student;

    public UserDetailsImpl(Student student) {

        this.student = student;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        boolean v = student.getIsActive();

        if (v) {
            authorities.add(new SimpleGrantedAuthority("STUDENT_ACTIVE"));
        } else {
            authorities.add(new SimpleGrantedAuthority("STUDENT_INACTIVE"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        return student.getUserName();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetailsImpl build(Student student)
    {

        return new UserDetailsImpl(student);


    }
}
