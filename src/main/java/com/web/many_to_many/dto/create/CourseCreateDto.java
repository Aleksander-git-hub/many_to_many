package com.web.many_to_many.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateDto {
    private Long id;

    private String title;

    private Boolean deleted;

    private List<StudentCreateDto> students;
}
