package com.brandy.courses.student;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    public StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public Student postStudent(
            @RequestBody Student student
    ) {
        return studentService.postStudent(student);
    }

    @PostMapping("/students-dto")
    public StudentResponseDto postStudentWithDto(
            @Valid @RequestBody StudentDto dto
    ) {
        return studentService.postStudentWithDto(dto);
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students-dto")
    public List<StudentResponseDto> findAllStudentsDtos() {
        return studentService.findAllStudentsResponseDtos();
    }

    @GetMapping("/students/{student-id}")
    public Student findById(
            @PathVariable("student-id") Integer id
    ) {
        return studentService.findById(id);
    }

    @GetMapping("/students-dto/{student-id}")
    public StudentResponseDto findByIdDto(
            @PathVariable("student-id") Integer id
    ) {
        return studentService.findByIdDto(id);
    }

    @GetMapping("/students/search/{search}")
    public List<Student> findByName(
            @PathVariable("search") String search
    ) {
        return studentService.findByName(search);
    }

    @GetMapping("/students-dto/search/{search}")
    public List<StudentResponseDto> findByNameDtos(
            @PathVariable("search") String search
    ) {
        return studentService.findByNameDtos(search);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(
            @PathVariable("student-id") Integer id
    ) {
        studentService.deleteById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
