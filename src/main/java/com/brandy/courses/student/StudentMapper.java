package com.brandy.courses.student;

import com.brandy.courses.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student studentDtoToStudent(StudentDto dto) {
        if (dto == null) {
            throw new NullPointerException("Student Dto should not be null");
        }
        var student = new Student();
        student.setEmail(dto.email());
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);

        return student;
    }

    public StudentResponseDto studentToStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }
}
