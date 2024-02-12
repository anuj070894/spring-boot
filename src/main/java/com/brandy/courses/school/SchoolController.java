package com.brandy.courses.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {
    public SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public School insert(
            @RequestBody School school
    ) {
        return this.schoolService.insert(school);
    }

    @PostMapping("/schools-dto")
    public SchoolResponseDto insertDto(
            @RequestBody SchoolDto dto
    ) {
        return this.schoolService.insertDto(dto);
    }

    @GetMapping("/schools")
    public List<School> findAll() {
        return this.schoolService.findAll();
    }

    @GetMapping("/schools-dto")
    public List<SchoolResponseDto> findAllSchoolDtos() {
        return this.schoolService.findAllSchoolDtos();
    }


}
