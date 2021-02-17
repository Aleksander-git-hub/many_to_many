package com.web.many_to_many.mapper;

import com.web.many_to_many.dto.create.CourseCreateDto;
import com.web.many_to_many.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

public abstract class CourseMapperDecorator implements CourseMapper {

    @Autowired
    private CourseMapper delegate;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public CourseEntity toEntity(CourseCreateDto courseCreateDto) {
        CourseEntity course = delegate.toEntity(courseCreateDto);
        if (!CollectionUtils.isEmpty(course.getStudents())) {
            course.setStudents(courseCreateDto.getStudents().stream()
                    .map(studentMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return course;
    }
}
