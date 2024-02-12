package com.brandy.courses.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    public SchoolMapper schoolMapper;
    public SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public School insert(
            School school
    ) {
        return this.schoolRepository.save(school);
    }

    public SchoolResponseDto insertDto(
            SchoolDto dto
    ) {
        var school = new School(dto.name());
        var savedSchool = this.schoolRepository.save(school);
        return schoolMapper.toSchoolResponseDto(savedSchool);
    }

    public List<School> findAll() {
        return this.schoolRepository.findAll();
    }


    public List<SchoolResponseDto> findAllSchoolDtos() {
        List<School> schools = this.schoolRepository.findAll();
        return schoolMapper.schoolsToSchoolDtos(schools);
    }
}
