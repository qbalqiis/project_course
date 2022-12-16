package com.gec.service;


import com.gec.entity.CourseType;
import com.gec.exception.EntityExistException;
import com.gec.repository.CourseTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICourseTypeService implements CourseTypeService{
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<CourseType> list(Integer page, Integer size, String direction, String sortBy) throws Exception {
        List<CourseType> courses = courseTypeRepository.findAll();
        return courses;
    }

    @Override
    public Page<CourseType> listCourseType(Integer page, Integer pageSize, String sortDescription, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        Page<CourseType> result = courseTypeRepository.findAll(pageable);
        return result;
    }

    @Override
    public CourseType createCourseType(CourseType courseType) throws Exception {
        try {
            CourseType newCourseType = courseTypeRepository.save(courseType);
            return newCourseType;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }

    }

    @Override
    public CourseType getBy(String id) throws Exception {
        Optional<CourseType> courseTypeList = courseTypeRepository.findById(id);
        if(courseTypeList.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return courseTypeList.get();
    }

    @Override
    public List<CourseType> findOne(String name) {
        Specification spec = new CourseTypeSpec().typeNameLike(name);
        List<CourseType> result = courseTypeRepository.findAll(spec);
        return result;
    }
}
