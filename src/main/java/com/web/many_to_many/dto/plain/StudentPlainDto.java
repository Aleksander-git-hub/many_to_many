package com.web.many_to_many.dto.plain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPlainDto {

    private Long id;

    private String firstName;

    private String secondName;

    private Integer age;

    private String gender;

    private String email;

    private Boolean deleted;
}
