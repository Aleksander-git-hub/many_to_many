package com.web.many_to_many.dto.plain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePlainDto {
    private Long id;

    private String title;

    private Boolean deleted;
}
