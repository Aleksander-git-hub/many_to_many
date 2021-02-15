package com.web.many_to_many.dto;

import com.web.many_to_many.dto.plain.CoursePlainDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;

    private String firstName;

    private String secondName;

    private Integer age;

    private String gender;

    private String email;

    private Boolean deleted;

    private List<CoursePlainDto> courses;
}
