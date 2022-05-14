package com.unitTest.Repository;

import com.unitTest.Constants.Enums.Course;
import com.unitTest.Constants.Enums.Department;
import com.unitTest.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByDepartment(Department department);
    Optional<Student> findByCourse(Course course);
    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Student c WHERE c.course =?1")
    boolean checkIfCourseExist(Course course);
}
