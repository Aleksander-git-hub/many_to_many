package com.web.many_to_many.controller;

import com.web.many_to_many.dto.StudentDto;
import com.web.many_to_many.dto.create.StudentCreateDto;
import com.web.many_to_many.dto.plain.StudentPlainDto;
import com.web.many_to_many.entity.StudentEntity;
import com.web.many_to_many.mapper.StudentMapper;
import com.web.many_to_many.mapper.plain.StudentPlainMapper;
import com.web.many_to_many.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentPlainMapper studentPlainMapper;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping(value = "/student")
    public StudentPlainDto saveStudent(@RequestBody StudentCreateDto studentCreateDto) {
        return studentPlainMapper.toPlainDto(studentService.saveStudent(studentCreateDto));
    }

    @GetMapping(value = "/student/{studentId}")
    public StudentDto getStudentById(@PathVariable Long studentId) {
        return studentMapper.toDto(studentService.getStudentById(studentId));
    }

    @GetMapping(value = "/students")
    public List<StudentPlainDto> getAllStudents() {
        List<StudentEntity> studentEntities = studentService.getAllStudents();
        return studentEntities.stream().map(studentPlainMapper::toPlainDto).collect(Collectors.toList());
    }

    @PutMapping(value = "/student/{studentId}")
    public StudentDto updateStudentById(@RequestBody StudentCreateDto studentCreateDto,
                                        @PathVariable Long studentId) {
        return studentMapper.toDto(studentService.updateStudentById(studentCreateDto, studentId));
    }

    @DeleteMapping(value = "/student/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/student/{studentId}/course/{courseId}")
    public StudentDto addCourseToStudent(@PathVariable Long studentId,
                                         @PathVariable Long courseId) {
        return studentMapper.toDto(studentService.addCourseToStudent(studentId, courseId));
    }

    @DeleteMapping(value = "/student/{studentId}/course/{courseId}")
    public StudentDto removeCourseFromStudent(@PathVariable Long studentId,
                                              @PathVariable Long courseId) {
        return studentMapper.toDto(studentService.removeCourseFromStudent(studentId, courseId));
    }
}
