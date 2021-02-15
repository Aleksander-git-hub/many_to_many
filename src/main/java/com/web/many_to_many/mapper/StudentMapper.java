package com.web.many_to_many.mapper;

import com.web.many_to_many.dto.StudentDto;
import com.web.many_to_many.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(StudentEntity studentEntity);
}
