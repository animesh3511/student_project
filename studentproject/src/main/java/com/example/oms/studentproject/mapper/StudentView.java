package com.example.oms.studentproject.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentView {

    private Long studentId;
    private double contact;
    private String email;
    private boolean isActive;
    private boolean isDeleted;
    private String location;
    private String name;
    private String password;
    private String username;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
