package com.brandy.courses.student;

import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @InjectMocks
    public StudentService studentService;

    @Mock
    public StudentMapper studentMapper;

    @Mock
    public StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_post_student_with_dto() {
        StudentDto studentDto = new StudentDto(
                "Anuj",
                "Kumar",
                "anuj@mail.com",
                1
        );

        Student student = new Student(
            "Anuj",
            "Kumar",
                "anuj@mail.com",
                30,
                null,
                null
        );

        Student savedStudent = new Student(
                "Anuj",
                "Kumar",
                "anuj@mail.com",
                30,
                null,
                null
        );

        savedStudent.setId(1);

        Mockito.when(studentMapper.studentDtoToStudent(studentDto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.studentToStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto(
                "Anuj",
                "Kumar",
                "anuj@mail.com"
        ));

        StudentResponseDto studentResponseDto = studentService.postStudentWithDto(studentDto);

        assertEquals(studentDto.firstname(), studentResponseDto.firstname());
        assertEquals(studentDto.lastname(), studentResponseDto.lastname());
        assertEquals(studentDto.email(), studentResponseDto.email());

        Mockito.verify(studentMapper, Mockito.times(1)).studentDtoToStudent(studentDto);
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
        Mockito.verify(studentMapper, Mockito.times(1)).studentToStudentResponseDto(savedStudent);
    }

//    public java.util.List<StudentResponseDto> findAllStudentsResponseDtos() {
//        return studentRepository.findAll()
//                .stream()
//                .map(studentMapper::studentToStudentResponseDto)
//                .collect(Collectors.toList());
//    }
    @Test
    public void should_find_all_students() {
        // given
        Student student = new Student(
                "Anuj",
                "Kumar",
                "anuj@mail.com",
                30,
                null,
                null
        );
        List<Student> students = new ArrayList<>();

        students.add(student);

        Mockito.when(studentRepository.findAll()).thenReturn(students);
        Mockito.when(studentMapper.studentToStudentResponseDto(Mockito.any(Student.class)))
                .thenReturn(new StudentResponseDto("Anuj", "Kumar", "anuj@mail.com"));

        // when
        List<StudentResponseDto> responseDtos = studentService.findAllStudentsResponseDtos();

        // then
        assertEquals(students.size(), responseDtos.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
        Mockito.verify(studentMapper, Mockito.times(1)).studentToStudentResponseDto(student);
    }

    /*
    public StudentResponseDto findByIdDto(
            Integer id
    ) {
        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentResponseDto)
                .orElse(null);
    }
     */
    @Test
    public void should_find_student_by_id() {
        // given
        Integer studentId = 1;
        Student student = new Student(
                "Anuj",
                "Kumar",
                "anuj@mail.com",
                30,
                null,
                null
        );

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        Mockito.when(studentMapper.studentToStudentResponseDto(Mockito.any(Student.class)))
                .thenReturn(new StudentResponseDto("Anuj", "Kumar", "anuj@mail.com"));

        // when
        StudentResponseDto responseDto = studentService.findByIdDto(studentId);

        // then
        assertEquals(student.getFirstname(), responseDto.firstname());
        assertEquals(student.getLastname(), responseDto.lastname());
        assertEquals(student.getEmail(), responseDto.email());
        Mockito.verify(studentRepository, Mockito.times(1)).findById(studentId);
        Mockito.verify(studentMapper, Mockito.times(1)).studentToStudentResponseDto(student);
    }

    /*
    public List<StudentResponseDto> findByNameDtos(
            String search
    ) {
        return studentRepository.findAllByFirstnameContaining(search)
                .stream()
                .map(studentMapper::studentToStudentResponseDto)
                .collect(Collectors.toList());
    }
     */
    @Test
    public void should_find_students_by_name_dtos() {
        // given
        String studentName = "Anuj";
        Student student = new Student(
                "Anuj",
                "Kumar",
                "anuj@mail.com",
                30,
                null,
                null
        );

        List<Student> students = new ArrayList<>();
        students.add(student);

        Mockito.when(studentRepository.findAllByFirstnameContaining(studentName)).thenReturn(students);
        Mockito.when(studentMapper.studentToStudentResponseDto(Mockito.any(Student.class)))
                .thenReturn(new StudentResponseDto("Anuj", "Kumar", "anuj@mail.com"));

        // when
        List<StudentResponseDto> responseDtos = studentService.findByNameDtos(studentName);

        // then
        assertEquals(students.size(), responseDtos.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAllByFirstnameContaining(studentName);
        Mockito.verify(studentMapper, Mockito.times(1)).studentToStudentResponseDto(student);
    }

    /*
    public void deleteById(
            Integer id
    ) {
        studentRepository.deleteById(id);
    }
     */
    @Test
    public void should_delete_student_by_id() {
        // given
        Integer studentId = 1;

        // when
        studentService.deleteById(studentId);

        // then
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(studentId);
    }
}