package com.example.student_management.dto;

import com.example.student_management.entity.Student;

public class StudentMapper {

    public static StudentResponseDTO toResponseDTO(Student student) {

        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        dto.setCourseId(student.getCourse().getCourseId());
        dto.setCourseName(student.getCourse().getCourseName());

        return dto;
    }
}