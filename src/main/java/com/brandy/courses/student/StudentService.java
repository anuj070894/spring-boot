package com.brandy.courses.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    public StudentRepository studentRepository;
    public StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Student postStudent(
            @RequestBody Student student
    ) {
        return studentRepository.save(student);
    }

    public StudentResponseDto postStudentWithDto(
            StudentDto dto
    ) {
        var student = studentMapper.studentDtoToStudent(dto);
        var repoStudent = studentRepository.save(student);
        return studentMapper.studentToStudentResponseDto(repoStudent);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<StudentResponseDto> findAllStudentsResponseDtos() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::studentToStudentResponseDto)
                .collect(Collectors.toList());
    }

    public Student findById(
            Integer id
    ) {
        return studentRepository.findById(id).orElse(null);
    }

    public StudentResponseDto findByIdDto(
            Integer id
    ) {
        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentResponseDto)
                .orElse(null);
    }

    public List<Student> findByName(
            String search
    ) {
        return studentRepository.findAllByFirstnameContaining(search);
    }

    public List<StudentResponseDto> findByNameDtos(
            String search
    ) {
        return studentRepository.findAllByFirstnameContaining(search)
                .stream()
                .map(studentMapper::studentToStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteById(
            Integer id
    ) {
        studentRepository.deleteById(id);
    }
}
