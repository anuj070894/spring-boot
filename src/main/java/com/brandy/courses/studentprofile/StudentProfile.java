package com.brandy.courses.studentprofile;

import com.brandy.courses.student.Student;
import jakarta.persistence.*;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer id;

    private String bio;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentProfile(String bio, Student student) {
        this.bio = bio;
        this.student = student;
    }

    public StudentProfile() {
    }
}
