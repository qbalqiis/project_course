package com.gec.service;
import com.gec.entity.CourseType;
import com.gec.repository.CourseTypeRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseTypeService {
    List<CourseType> list(Integer page, Integer size, String direction, String sortBy) throws Exception;

    Page<CourseType> listCourseType(Integer page, Integer pageSize, String sortDescription, String orderBy) throws  Exception;
    CourseType createCourseType(CourseType courseType) throws Exception;
    CourseType getBy(String id) throws Exception;
    List<CourseType> findOne (String name);

}
