package com.web.many_to_many.mapper;

import com.web.many_to_many.dto.CourseDto;
import com.web.many_to_many.entity.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toDto(CourseEntity courseEntity);
}
