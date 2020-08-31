package com.nbu.edu.cn.utils.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private String birth;

    private List<CourseAndScoreDTO> courseAndScoreDTOList;
}
