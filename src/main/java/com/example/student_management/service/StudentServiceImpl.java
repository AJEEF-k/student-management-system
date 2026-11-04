package com.example.student_management.service;

import org.springframework.stereotype.Service;
import com.example.student_management.dto.StudentMapper;
import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import java.util.List; 
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.student_management.entity.Course;
import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.repository.CourseRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

 
    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO studentDTO) {
    	
    	
    	if (studentRepository.existsByEmail(studentDTO.getEmail())) {
    	    throw new ResponseStatusException(
    	        HttpStatus.CONFLICT, "Email already exists"
    	    );
    	}

        Course course = courseRepository.findById(studentDTO.getCourseId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Course not found"
            ));

        Student student = new Student();

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setCourse(course);

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.toResponseDTO(savedStudent);
    }
    
    
    @Override
    public List<StudentResponseDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }
    
    @Override
    public StudentResponseDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found"
            ));

        return StudentMapper.toResponseDTO(student);
    }
    
    
    @Override
    public List<StudentResponseDTO> getStudentsByCourseId(Long courseId) {

        if (!courseRepository.existsById(courseId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Course not found"
            );
        }

        return studentRepository.findByCourse_CourseId(courseId)
                .stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }
    
    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentDTO) {

        Student existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found"
            ));
        
        if (studentRepository.existsByEmail(studentDTO.getEmail())
                && !existingStudent.getEmail().equalsIgnoreCase(studentDTO.getEmail())) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Email already exists"
            );
        }
        

        Course course = courseRepository.findById(studentDTO.getCourseId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Course not found"
            ));

        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setAge(studentDTO.getAge());
        existingStudent.setCourse(course);

        Student updatedStudent = studentRepository.save(existingStudent);

        return StudentMapper.toResponseDTO(updatedStudent);
    } 
    
    
    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found"
            ));

        studentRepository.delete(student);
    }
}