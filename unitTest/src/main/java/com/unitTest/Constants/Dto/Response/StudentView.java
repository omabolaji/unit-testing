package com.unitTest.Constants.Dto.Response;

import com.unitTest.Constants.Enums.Course;
import com.unitTest.Constants.Enums.Department;
import com.unitTest.Constants.Enums.Gender;
import com.unitTest.Constants.Enums.LEVEL;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentView {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String name;
    private String email;
    private Gender gender;
    private LocalDateTime dob;
    private Department department;
    private LEVEL level;
    private Course course;
    private Integer version;
    private boolean active;
    private boolean disable;
}
