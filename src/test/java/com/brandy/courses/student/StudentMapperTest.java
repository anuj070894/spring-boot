package com.brandy.courses.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    public StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldConvertStudentDtoToStudent() {
        StudentDto dto = new StudentDto(
                "Anuj",
                "Kumar",
                "anuj@monet.work",
                1
        );

        Student student = studentMapper.studentDtoToStudent(dto);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldConvertStudentToStudentResponseDto() {
        Student student = new Student(
                "Anuj",
                "Kumar",
                "anuj@monet.work",
                1,
                null,
                null
        );

        StudentResponseDto dto = studentMapper.studentToStudentResponseDto(student);

        assertEquals(student.getFirstname(), dto.firstname());
        assertEquals(student.getLastname(), dto.lastname());
        assertEquals(student.getEmail(), dto.email());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenConvertingNullDtoToStudent() {
        var exp = assertThrows(NullPointerException.class, () -> studentMapper.studentDtoToStudent(null));
        assertEquals("Student Dto should not be null", exp.getMessage());
    }

}