package com.example.oms.studentproject.Configuration;


import com.example.oms.studentproject.Configuration.jwttoken.JwtTokenFilter;
import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Serviceimpl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.oms.studentproject.Model.Student;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;





    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(studentId -> {

         Long id= Long.parseLong(studentId);
          Student student = studentRepository.findById(id)
                 .orElseThrow(()-> new UsernameNotFoundException("student not found with id: "+id));

         UserDetails userDetails =UserDetailsImpl.build(student);

          return userDetails;
             }
     );


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity = httpSecurity.cors().and().csrf().disable();

        httpSecurity = httpSecurity.sessionManagement()
                                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                    .and();

        httpSecurity = httpSecurity.exceptionHandling()
                                   .authenticationEntryPoint((request, response, ex) -> {

                                       response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                               ex.getMessage());



                                   } )

                                   .and();

                      httpSecurity.authorizeRequests()

                              .antMatchers(HttpMethod.GET, "/api/findById/**").hasAuthority("STUDENT_ACTIVE")
                              // .antMatchers(HttpMethod.GET,"api/findById/**").hasRole("STUDENT_ACTIVE")

                              .antMatchers(HttpMethod.POST,"/api/**").permitAll()
                                   .antMatchers("/api/login/**").permitAll()
                              .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll() // Allow access to Swagger UI


                              .anyRequest().authenticated();

                      httpSecurity.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);



    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
