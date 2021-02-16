package com.web.many_to_many.mapper;

import com.web.many_to_many.dto.StudentDto;
import com.web.many_to_many.dto.create.StudentCreateDto;
import com.web.many_to_many.dto.plain.StudentPlainDto;
import com.web.many_to_many.entity.StudentEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
@DecoratedWith(StudentMapperDecorator.class)
public interface StudentMapper {

    StudentDto toDto(StudentEntity studentEntity);

    StudentPlainDto toPlainDto(StudentEntity studentEntity);

    StudentEntity toEntity(StudentCreateDto studentCreateDto);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updatingStudentEntityFromStudentCreateDto(StudentCreateDto source,
                                                   @MappingTarget StudentEntity target);
}
