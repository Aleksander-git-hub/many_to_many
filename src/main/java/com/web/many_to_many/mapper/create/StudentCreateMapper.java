package com.web.many_to_many.mapper.create;

import com.web.many_to_many.dto.create.StudentCreateDto;
import com.web.many_to_many.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentCreateMapper {

    StudentEntity toEntity(StudentCreateDto studentCreateDto);

    StudentCreateDto toDtoCreate(StudentEntity studentEntity);
}
