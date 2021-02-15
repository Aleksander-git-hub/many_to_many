package com.web.many_to_many.mapper.create;

import com.web.many_to_many.dto.create.CourseCreateDto;
import com.web.many_to_many.entity.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseCreateMapper {

    CourseEntity toEntity(CourseCreateDto courseCreateDto);

    CourseCreateDto toDtoCreate(CourseEntity courseEntity);
}
