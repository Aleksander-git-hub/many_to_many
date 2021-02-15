package com.web.many_to_many.mapper.plain;

import com.web.many_to_many.dto.plain.StudentPlainDto;
import com.web.many_to_many.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentPlainMapper {

    StudentPlainDto toPlainDto(StudentEntity studentEntity);
}
