package com.web.many_to_many.mapper;

import com.web.many_to_many.dto.CourseDto;
import com.web.many_to_many.dto.create.CourseCreateDto;
import com.web.many_to_many.dto.plain.CoursePlainDto;
import com.web.many_to_many.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toDto(CourseEntity courseEntity);

    CourseEntity toEntity(CourseCreateDto courseCreateDto);

    CoursePlainDto toPlainDto(CourseEntity courseEntity);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updatingCourseEntityFromCourseCreateDto(CourseCreateDto source,
                                                 @MappingTarget CourseEntity target);
}
