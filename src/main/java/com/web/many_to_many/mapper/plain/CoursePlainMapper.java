package com.web.many_to_many.mapper.plain;

import com.web.many_to_many.dto.plain.CoursePlainDto;
import com.web.many_to_many.entity.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoursePlainMapper {

    CoursePlainDto toPlainDto(CourseEntity courseEntity);
}
