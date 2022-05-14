package com.unitTest.Constants.Dto.Request;

import com.unitTest.Constants.Enums.Course;
import com.unitTest.Constants.Enums.Department;
import com.unitTest.Constants.Enums.Gender;
import com.unitTest.Constants.Enums.LEVEL;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDto {
    private String name;
    private String email;
    private Gender gender;
    private LocalDateTime dob;
    private Department department;
    private LEVEL level;
    private Course course;
}
