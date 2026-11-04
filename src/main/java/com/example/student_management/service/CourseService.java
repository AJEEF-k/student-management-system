package com.example.student_management.service;

import java.util.List;
import com.example.student_management.entity.Course;

public interface CourseService {

    Course addCourse(Course course);

    List<Course> getAllCourses();
    
    Course getCourseById(Long courseId);

    Course updateCourse(Long courseId, Course course);

    void deleteCourse(Long courseId);
}