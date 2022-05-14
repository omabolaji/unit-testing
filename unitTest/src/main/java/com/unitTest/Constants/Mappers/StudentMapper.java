package com.unitTest.Constants.Mappers;

import com.unitTest.Constants.Dto.Request.StudentDto;
import com.unitTest.Constants.Dto.Response.StudentView;
import com.unitTest.Models.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    public abstract Student regStudent(StudentDto dto);
    public abstract StudentView studentView(Student student);
    public abstract List<StudentView> studentView(List<Student> students);

}
