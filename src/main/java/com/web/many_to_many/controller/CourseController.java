package com.web.many_to_many.controller;

import com.web.many_to_many.dto.CourseDto;
import com.web.many_to_many.dto.create.CourseCreateDto;
import com.web.many_to_many.dto.plain.CoursePlainDto;
import com.web.many_to_many.entity.CourseEntity;
import com.web.many_to_many.mapper.CourseMapper;
import com.web.many_to_many.mapper.plain.CoursePlainMapper;
import com.web.many_to_many.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CoursePlainMapper coursePlainMapper;

    @Autowired
    private CourseMapper courseMapper;

    @PostMapping(value = "/course")
    public CoursePlainDto saveCourse(@RequestBody CourseCreateDto courseCreateDto) {
        return coursePlainMapper.toPlainDto(courseService.saveCourse(courseCreateDto));
    }

    @GetMapping(value = "/course/{courseId}")
    public CourseDto getCourseById(@PathVariable Long courseId) {
        return courseMapper.toDto(courseService.getCourseById(courseId));
    }

    @GetMapping(value = "/courses")
    public List<CoursePlainDto> getAllCourses() {
        List<CourseEntity> courseEntities = courseService.getAllCourses();
        return courseEntities.stream().map(coursePlainMapper::toPlainDto).collect(Collectors.toList());
    }

    @PutMapping(value = "/course/{courseId}")
    public CourseDto updateCourseById(@RequestBody CourseCreateDto courseCreateDto,
                                      @PathVariable Long courseId) {
        return courseMapper.toDto(courseService.updateCourseById(courseCreateDto, courseId));
    }

    @DeleteMapping(value = "/course/{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok().build();
    }
}
