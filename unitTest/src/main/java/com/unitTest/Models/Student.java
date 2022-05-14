package com.unitTest.Models;

import com.unitTest.Constants.Enums.Course;
import com.unitTest.Constants.Enums.Department;
import com.unitTest.Constants.Enums.Gender;
import com.unitTest.Constants.Enums.LEVEL;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Version
    private Integer version=1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreatedDate
    private LocalDateTime createDate=LocalDateTime.now();
    @LastModifiedDate
    private LocalDateTime updateDate=LocalDateTime.now();
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private LocalDateTime dob;
    @NotNull
    private Department department;
    @NotNull
    private LEVEL level;
    @NotNull
    private Course course;
    private boolean disable=false;
    private boolean active=true;
}
