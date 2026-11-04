package com.example.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.student_management.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	 
	boolean existsByCourseName(String courseName);
	
	boolean existsByCourseNameAndCourseIdNot(String courseName, Long courseId);  

}   