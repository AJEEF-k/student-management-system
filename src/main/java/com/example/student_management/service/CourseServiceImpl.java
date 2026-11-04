package com.example.student_management.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.student_management.entity.Course;
import com.example.student_management.repository.CourseRepository;
import com.example.student_management.repository.StudentRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(
            CourseRepository courseRepository,
            StudentRepository studentRepository) {

        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Course addCourse(Course course) {

        if (courseRepository.existsByCourseName(course.getCourseName())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Course already exists"
            );
        }

        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {

        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Course not found"
                ));
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {

        Course existingCourse = getCourseById(courseId);

        if (courseRepository.existsByCourseNameAndCourseIdNot(
                course.getCourseName(), courseId)) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Course name already exists"
            );
        }

        existingCourse.setCourseName(course.getCourseName());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {    

        Course existingCourse = getCourseById(courseId);

        if (studentRepository.existsByCourse_CourseId(courseId)) {   

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Cannot delete course because students are assigned to it"
            );
        }

        courseRepository.delete(existingCourse);
    }
}