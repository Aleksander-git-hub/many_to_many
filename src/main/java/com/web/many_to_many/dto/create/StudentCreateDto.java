package com.web.many_to_many.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateDto {
    private Long id;

    private String firstName;

    private String secondName;

    private Integer age;

    private String gender;

    private String email;

    private Boolean deleted = false;

    private List<CourseCreateDto> courses;
}
