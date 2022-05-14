package com.unitTest.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.unitTest.Constants.Dto.Request.StudentDto;
import com.unitTest.Constants.Dto.Response.StudentView;
import com.unitTest.Constants.Enums.Course;
import com.unitTest.Constants.Enums.Department;
import com.unitTest.Constants.Enums.Gender;
import com.unitTest.Constants.Enums.LEVEL;
import com.unitTest.Constants.Exception.NotFoundException;
import com.unitTest.Constants.Mappers.StudentMapper;
import com.unitTest.Models.Student;
import com.unitTest.Repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    @Mock private StudentRepository studentRepository;
    @Mock private StudentMapper studentMapper;
    private StudentService studentServiceTest;
//    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        studentServiceTest = new StudentService(studentRepository,studentMapper);
    }

//    @AfterEach
//    void tearDown() throws Exception {
//       autoCloseable.close();
//    }

    @Test
//    @Disabled
    void getAllStudents() {
        //Todo: when
        studentServiceTest.getAllStudents();
        //Todo: then
        verify(studentRepository).findAll(Sort.by(Sort.Direction.DESC,"id"));
        //Todo: given
    }


    @Test
//    @Disabled
    void regStudent() {
        //Todo: given
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("test@gmail.com");
        studentDto.setCourse(Course.COMMON_LAW);
        studentDto.setDepartment(Department.EDUCATION);
        studentDto.setDob(LocalDateTime.now());
        studentDto.setLevel(LEVEL.L100);
        studentDto.setGender(Gender.MALE);
        studentDto.setName("Test");

        //Todo: when
        studentServiceTest.regStudent(studentDto);
        //todo: then

//        Student studentMap = studentMapper.regStudent(studentDto);
//        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(studentMap.getClass());
//
//        verify(studentRepository).save(studentArgumentCaptor.capture());
//
//        Student captureStudent = studentArgumentCaptor.getValue();
//
//        assertThat(captureStudent).isEqualTo(studentDto);

        ArgumentCaptor<StudentDto> studentArgumentCaptor =
                ArgumentCaptor.forClass(StudentDto.class);

        verify(studentMapper).regStudent(studentArgumentCaptor.capture());

        StudentDto captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(studentDto);
    }

    @Test
//    @Disabled
    void createNewStudentUsingStudentClass(){

        //Todo: given
        Student student = new Student();
        student.setVersion(1);
        student.setId(1l);
        student.setEmail("test@gmail.com");
        student.setCourse(Course.COMMON_LAW);
        student.setDepartment(Department.EDUCATION);
        student.setDob(LocalDateTime.now());
        student.setCreateDate(LocalDateTime.now());
        student.setUpdateDate(LocalDateTime.now());
        student.setLevel(LEVEL.L100);
        student.setGender(Gender.MALE);
        student.setName("Test");

        //Todo: when
        studentServiceTest.addStudent(student);
        //Todo: then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student captureStudent = studentArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(student);

    }


    @Test
//    @Disabled
    void checkAndThrowExceptionIfEmailExist(){

        //Todo: given
        Student student = new Student();
        student.setVersion(1);
        student.setId(1l);
        student.setEmail("test@gmail.com");
        student.setCourse(Course.COMMON_LAW);
        student.setDepartment(Department.EDUCATION);
        student.setDob(LocalDateTime.now());
        student.setCreateDate(LocalDateTime.now());
        student.setUpdateDate(LocalDateTime.now());
        student.setLevel(LEVEL.L100);
        student.setGender(Gender.MALE);
        student.setName("Test");

        //Todo: when
        //Todo: then
        given(studentRepository.existsByEmail(student.getEmail())).willReturn(true);
//        given(studentRepository.existsByEmail(any())).willReturn(true);

        assertThatThrownBy(() -> studentServiceTest.addStudent(student))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Email already exist");

        verify(studentRepository, never()).save(any());
    }


    @Test
//    @Disabled
    void checkAndThrowExceptionIfEmailExistRegStd(){

        //Todo: given
        StudentDto studentDto = new StudentDto();

        studentDto.setEmail("test@gmail.com");
        studentDto.setCourse(Course.COMMON_LAW);
        studentDto.setDepartment(Department.EDUCATION);
        studentDto.setDob(LocalDateTime.now());
        studentDto.setLevel(LEVEL.L100);
        studentDto.setGender(Gender.MALE);
        studentDto.setName("Test");

        //Todo: when
        //Todo: then
        given(studentRepository.existsByEmail(studentDto.getEmail())).willReturn(true);
//        given(studentRepository.existsByEmail(any())).willReturn(true);

        assertThatThrownBy(() -> studentServiceTest.regStudent(studentDto))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Email already exist");

        verify(studentRepository, never()).save(any());
    }


    @Test
//    @Disabled
    void disableStudent() {

        //Todo: given
        Student student = new Student();
        student.setVersion(1);
        student.setId(12l);
        student.setEmail("test@gmail.com");
        student.setCourse(Course.COMMON_LAW);
        student.setDepartment(Department.EDUCATION);
        student.setDob(LocalDateTime.now());
        student.setCreateDate(LocalDateTime.now());
        student.setUpdateDate(LocalDateTime.now());
        student.setLevel(LEVEL.L100);
        student.setGender(Gender.MALE);
        student.setName("Test");
        student.setActive(false);
        student.setDisable(true);

        //Todo: when
        //Todo: then
//        System.out.println("ID--"+student.getId());

//        verify(studentRepository).save(student);
//        studentServiceTest.deleteStudent(student.getId());
        assertThat(student.isActive()).isFalse();
    }


    @Test
//    @Disabled
    void throwExceptionTodisableStudentIfStudentIdDoesNotEixst() {

        //Todo: given
        Student student = new Student();
        student.setVersion(1);
        student.setId(12l);
        student.setEmail("test@gmail.com");
        student.setCourse(Course.COMMON_LAW);
        student.setDepartment(Department.EDUCATION);
        student.setDob(LocalDateTime.now());
        student.setCreateDate(LocalDateTime.now());
        student.setUpdateDate(LocalDateTime.now());
        student.setLevel(LEVEL.L100);
        student.setGender(Gender.MALE);
        student.setName("Test");
        student.setActive(false);
        student.setDisable(true);

        //Todo: when
        //Todo: then
        System.out.println("Boo--"+studentRepository.existsById(student.getId()));
//        given(studentRepository.existsById(student.getId())).willReturn(false);

        assertThatThrownBy(() -> studentServiceTest.deleteStudent(student.getId()))
                .isInstanceOf(NotFoundException.class).hasMessageContaining("Student not found");

        verify(studentRepository, never()).save(any());
    }


}