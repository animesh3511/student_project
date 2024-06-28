package com.example.oms.studentproject.Controller;

import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Model.request.AuthRequest;
import com.example.oms.studentproject.Serviceimpl.UserDetailsImpl;
import com.example.oms.studentproject.mapper.StudentViewMapper;
import com.example.oms.studentproject.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Authentication")
@RestController
@RequestMapping("/api")
public class AuthApi {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentViewMapper studentViewMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody  AuthRequest authRequest)
    {
     try{

         Authentication authenticate = authenticationManager.authenticate(new
                 UsernamePasswordAuthenticationToken(authRequest.getStudentId(),authRequest.getPassword()));

         //Student student = (Student) authenticate.getPrincipal();
         UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
         Student student = userDetails.getStudent();
         Long studentId = student.getStudentId();

         return ResponseEntity.ok()
                 .header(
                         HttpHeaders.AUTHORIZATION,
                         jwtTokenUtil.generateToken(studentId)
                 )
                 .body(studentViewMapper.toStudentView(student));


    }catch (BadCredentialsException ex)
     {

         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
     }
    }

}
