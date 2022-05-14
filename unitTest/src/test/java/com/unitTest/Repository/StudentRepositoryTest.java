package com.unitTest.Repository;

import com.unitTest.Constants.Enums.Course;
import com.unitTest.Constants.Enums.Department;
import com.unitTest.Constants.Enums.Gender;
import com.unitTest.Constants.Enums.LEVEL;
import com.unitTest.Models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepoTest;

    @AfterEach
    void tearDown(){
        studentRepoTest.deleteAll();
    }

    @Test
    void checkIfEmailExist() {
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
        studentRepoTest.save(student);
        //Todo: when
        boolean exist = studentRepoTest.existsByEmail(student.getEmail());
        System.out.println("Result--"+exist);
        //Todo: then
        assertThat(exist).isTrue();
    }

    @Test
    void checkIfEmailDoesNotExist() {
        //Todo: given
        String email = "yes@gmail.com";
        //Todo: when
        boolean exist = studentRepoTest.existsByEmail(email);
        System.out.println("Result--"+exist);
        //Todo: then
        assertThat(exist).isFalse();
    }

    @Test
    void checkIfCourseExist() {
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
        student = studentRepoTest.save(student);
        //Todo: when
        boolean exist = studentRepoTest.checkIfCourseExist(student.getCourse());
        System.out.println("Result--"+exist);
        //Todo: then
        assertThat(exist).isTrue();
    }

    @Test
    void checkIfCourseDoesNotExist() {
        //Todo: given
        Course course = Course.PHYSICS;
        //Todo: when
        boolean exist = studentRepoTest.checkIfCourseExist(course);
        System.out.println("Result--"+exist);
        //Todo: then
        assertThat(exist).isFalse();
    }
}