package com.unitTest.Services;

import com.unitTest.Constants.Dto.Request.StudentDto;
import com.unitTest.Constants.Dto.Response.StudentView;
import com.unitTest.Constants.Exception.NotFoundException;
import com.unitTest.Constants.Mappers.StudentMapper;
import com.unitTest.Models.Student;
import com.unitTest.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Component
public class StudentService {


    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentView regStudent(StudentDto dto){
        boolean isTaken = studentRepository.existsByEmail(dto.getEmail());
        if(isTaken){
            throw  new NotFoundException("Email already exist");
        }
        Student student = studentMapper.regStudent(dto);
        return studentMapper.studentView(studentRepository.save(student));
    }



    public Student addStudent(Student student){
        if(student.getEmail() != null){
            boolean isTaken = studentRepository.existsByEmail(student.getEmail());
            if(isTaken){
                throw  new NotFoundException("Email already exist");
            }
        }
        return studentRepository.save(student);
    }

    public List<StudentView> getAllStudents(){
        List<Student> students = studentRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        if(students == null){
            return null;
        }
        return studentMapper.studentView(students);
    }


    public StudentView deleteStudent(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            throw new NotFoundException("Student not found");
        }
        student.get().setActive(false);
        student.get().setDisable(true);
        student.get().setUpdateDate(LocalDateTime.now());
        return studentMapper.studentView(studentRepository.save(student.get()));
    }

}
