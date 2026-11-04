package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import java.util.List;

public interface StudentService {

	StudentResponseDTO addStudent(StudentRequestDTO studentDTO);
    
	List<StudentResponseDTO> getAllStudents();
    
	StudentResponseDTO getStudentById(Long id);
    
	List<StudentResponseDTO> getStudentsByCourseId(Long courseId);
    
	StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentDTO); 
    
    void deleteStudent(Long id);

}