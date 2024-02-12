package com.brandy.courses.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolMapper {
    public SchoolResponseDto toSchoolResponseDto(School school) {
        return new SchoolResponseDto(school.getName());
    }

    public List<SchoolResponseDto> schoolsToSchoolDtos(List<School> schools) {
        return schools
                .stream()
                .map(this::toSchoolResponseDto)
                .collect(Collectors.toList());
    }
}
