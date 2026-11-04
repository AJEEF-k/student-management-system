package com.example.student_management.controller;

import org.springframework.web.bind.annotation.*;
import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;

import jakarta.validation.Valid;
import java.util.List; 
import org.springframework.http.ResponseEntity;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDTO addStudent(
            @Valid @RequestBody StudentRequestDTO studentDTO) {

        return studentService.addStudent(studentDTO);
    }
    
    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    
    
    @GetMapping("/course/{courseId}")
    public List<StudentResponseDTO> getStudentsByCourseId(
            @PathVariable Long courseId) {

        return studentService.getStudentsByCourseId(courseId);
    }
    
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentDTO) {

        return studentService.updateStudent(id, studentDTO);
    }    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();  
    }
}