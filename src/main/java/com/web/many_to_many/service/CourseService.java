package com.web.many_to_many.service;

import com.web.many_to_many.dto.create.CourseCreateDto;
import com.web.many_to_many.entity.CourseEntity;
import com.web.many_to_many.exceptions.NotFoundException;
import com.web.many_to_many.mapper.create.CourseCreateMapper;
import com.web.many_to_many.repository.CourseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseCreateMapper courseCreateMapper;

    @Transactional(rollbackFor = NotFoundException.class)
    public CourseEntity saveCourse(CourseCreateDto courseCreateDto) {
        if (StringUtils.isEmpty(courseCreateDto.getTitle())) {
            throw new NotFoundException("Field are empty! Please, check this!");
        }
        courseCreateDto.setDeleted(false);
        return courseRepository.save(courseCreateMapper.toEntity(courseCreateDto));
    }

    public CourseEntity getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found for id: " + courseId));
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor = NotFoundException.class)
    public CourseEntity updateCourseById(CourseCreateDto courseCreateDto,
                                         Long courseId) {
        CourseEntity existingCourse = getCourseById(courseId);
        if (StringUtils.isEmpty(courseCreateDto.getTitle())) {
            throw new NotFoundException("Field are empty! Please, check this!");
        }
        if (existingCourse.getDeleted()) {
            throw new NotFoundException("Course is deleted already for id: " + courseId);
        }
        return courseRepository.save(updateExistingCourse(existingCourse, courseCreateDto));
    }

    private CourseEntity updateExistingCourse
            (CourseEntity existingCourse, CourseCreateDto courseCreateDto) {
        existingCourse.setTitle(courseCreateDto.getTitle());
        existingCourse.setDeleted(courseCreateDto.getDeleted());
        existingCourse.setStudents(courseCreateMapper.toEntity(courseCreateDto).getStudents());
        return existingCourse;
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor = NotFoundException.class)
    public void deleteCourseById(Long courseId) {
        CourseEntity existingCourse = getCourseById(courseId);
        if (existingCourse.getDeleted() != null || existingCourse.getDeleted()) {
            throw new NotFoundException("Course is deleted already for id: " + courseId);
        }
        existingCourse.setDeleted(true);
        courseRepository.save(existingCourse);
    }
}
