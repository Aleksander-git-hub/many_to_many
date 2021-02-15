package com.web.many_to_many.dto;

import com.web.many_to_many.dto.plain.StudentPlainDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;

    private String title;

    private Boolean deleted;

    private List<StudentPlainDto> students;
}
