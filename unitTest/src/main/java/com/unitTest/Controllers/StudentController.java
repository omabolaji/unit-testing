package com.unitTest.Controllers;

import com.unitTest.Constants.Dto.Request.StudentDto;
import com.unitTest.Constants.Dto.Response.ApiResponse;
import com.unitTest.Constants.Dto.Response.StudentView;
import com.unitTest.Services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/student")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/reg")
    public ApiResponse regStudent(@Valid @RequestBody StudentDto data){
        ApiResponse response = new ApiResponse();
        try{
            StudentView studentView = studentService.regStudent(data);
            response.setDescription("Operation successful");
            response.setData(studentView);
            response.setSuccess(true);
            return response;
        }catch (Exception ex){
            System.out.println("err--"+ex);
            ex.printStackTrace();
            response.setDescription("An error occurred!");
            response.setData(null);
            response.setSuccess(false);
            return response;
        }
    }

    @GetMapping(path = "/")
    public ApiResponse getStudents(){
        ApiResponse response = new ApiResponse();
        try{
            List<StudentView> students = studentService.getAllStudents();
            response.setDescription("Operation successful");
            response.setData(students);
            response.setSuccess(true);
            return response;
        }catch (Exception ex){
            System.out.println("err--"+ex);
            ex.printStackTrace();
            response.setDescription("An error occurred!");
            response.setData(null);
            response.setSuccess(false);
            return response;
        }
    }


    @DeleteMapping(path = "/disable/{studentId}")
    public ApiResponse deleteStudent(@Valid @PathVariable Long studentId){
        ApiResponse response = new ApiResponse();
        try{
            StudentView students = studentService.deleteStudent(studentId);
            response.setDescription("Operation successful");
            response.setData(students);
            response.setSuccess(true);
            return response;
        }catch (Exception ex){
            System.out.println("err--"+ex);
            ex.printStackTrace();
            response.setDescription("An error occurred!");
            response.setData(null);
            response.setSuccess(false);
            return response;
        }
    }
}
