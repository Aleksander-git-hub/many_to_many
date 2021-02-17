package com.web.many_to_many.mapper;

import com.web.many_to_many.dto.create.StudentCreateDto;
import com.web.many_to_many.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

public abstract class StudentMapperDecorator implements StudentMapper {

    @Autowired
    private StudentMapper delegate;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public StudentEntity toEntity(StudentCreateDto studentCreateDto) {
        StudentEntity student = delegate.toEntity(studentCreateDto);
        if (CollectionUtils.isEmpty(student.getCourses())) {
            student.setCourses(studentCreateDto.getCourses().stream()
                    .map(courseMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return student;
    }
}
