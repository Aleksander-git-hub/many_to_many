package com.web.many_to_many.service;

import com.web.many_to_many.dto.create.StudentCreateDto;
import com.web.many_to_many.entity.CourseEntity;
import com.web.many_to_many.entity.StudentEntity;
import com.web.many_to_many.exceptions.AlreadyAssignException;
import com.web.many_to_many.exceptions.NotFoundException;
import com.web.many_to_many.mapper.create.StudentCreateMapper;
import com.web.many_to_many.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCreateMapper studentCreateMapper;

    @Autowired
    private CourseService courseService;

    public StudentEntity saveStudent(StudentCreateDto studentCreateDto) {
        if (StringUtils.isEmpty(studentCreateDto.getFirstName()) ||
            StringUtils.isEmpty(studentCreateDto.getSecondName()) ||
            StringUtils.isEmpty(studentCreateDto.getEmail())) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        studentCreateDto.setDeleted(false);
        return studentRepository.save(studentCreateMapper.toEntity(studentCreateDto));
    }

    @Transactional
    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found for id: " + studentId));
    }

    @Transactional
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public StudentEntity updateStudentById(StudentCreateDto studentCreateDto,
                                           Long studentId) {
        StudentEntity existingStudent = getStudentById(studentId);
        if (StringUtils.isEmpty(studentCreateDto.getFirstName()) ||
                StringUtils.isEmpty(studentCreateDto.getSecondName()) ||
                StringUtils.isEmpty(studentCreateDto.getEmail())) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
        if (existingStudent.getDeleted()) {
            throw new NotFoundException("Student is deleted already for id: " + studentId);
        }
        return studentRepository.save(updateExistingStudent(existingStudent, studentCreateDto));
    }

    private StudentEntity updateExistingStudent
            (StudentEntity existingStudent, StudentCreateDto studentCreateDto) {
        existingStudent.setFirstName(studentCreateDto.getFirstName());
        existingStudent.setSecondName(studentCreateDto.getSecondName());
        existingStudent.setAge(studentCreateDto.getAge());
        existingStudent.setEmail(studentCreateDto.getEmail());
        existingStudent.setGender(studentCreateDto.getGender());
        existingStudent.setDeleted(studentCreateDto.getDeleted());
        existingStudent.setCourses(studentCreateMapper.toEntity(studentCreateDto).getCourses());
        return existingStudent;
    }

    @Transactional
    public void deleteStudentById(Long studentId) {
        StudentEntity existingStudent = getStudentById(studentId);
        if (existingStudent.getDeleted() != null || existingStudent.getDeleted()) {
            throw new NotFoundException("Student is deleted already for id: " + studentId);
        }
        existingStudent.setDeleted(true);
        studentRepository.save(existingStudent);
    }

    @Transactional
    public StudentEntity addCourseToStudent(Long studentId, Long courseId) {
        StudentEntity existingStudent = getStudentById(studentId);
        CourseEntity existingCourse = courseService.getCourseById(courseId);
        if (existingStudent.getDeleted()) {
            throw new NotFoundException
                    ("Can't add course! Student was deleted for id: " + studentId);
        }
        if (existingStudent.getCourses().contains(existingCourse)) {
            throw new AlreadyAssignException
                    ("Student with id: " + studentId + " already assign to course for id :" + courseId);
        }
        existingStudent.getCourses().add(existingCourse);
        return studentRepository.save(existingStudent);
    }

    @Transactional
    public StudentEntity removeCourseFromStudent(Long studentId, Long courseId) {
        StudentEntity existingStudent = getStudentById(studentId);
        CourseEntity existingCourse = courseService.getCourseById(courseId);
        if (!existingStudent.getCourses().contains(existingCourse)) {
            throw new NotFoundException
                    ("Student with id: " + studentId + " not assign to course for id :" + courseId);
        }
        existingStudent.getCourses().remove(existingCourse);
        return studentRepository.save(existingStudent);
    }
}
