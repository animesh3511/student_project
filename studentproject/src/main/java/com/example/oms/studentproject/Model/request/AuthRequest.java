package com.example.oms.studentproject.Model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthRequest {

   // @NotEmpty(message = "UserName cannot be empty")
    private Long studentId;

   // @NotEmpty(message = "password cannot be empty")
    private String password;


}
