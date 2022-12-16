package com.gec.service;

import com.gec.entity.Course;
import com.gec.entity.CourseType;
import com.gec.exception.NotFoundException;
import com.gec.repository.CourseRepository;
import com.gec.repository.CourseTypeRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICourseService implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseTypeRepository courseTypeRepository;

    public ICourseService(CourseRepository courseRepository, ModelMapper modelMapper, CourseTypeRepository courseTypeRepository) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public List<Course> list() throws Exception {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    @Override
    public Page<Course> list(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort=Sort.by(Sort.Direction.valueOf(direction),sortBy);
        Pageable pageable =PageRequest.of((page-1),size,sort);
        Page<Course> result = courseRepository.findAll(pageable);
        return result;
    }

    @Override
    public List<Course> findByTitleContains(String value) {
        List<Course> courses = courseRepository.findByTitleContains(value);
        if (courses.isEmpty()){
            throw new NotFoundException("Sorry Course with" + value + "tittle is not found");
        }
        return courses;
    }

    @Override
    public List<Course> findByDescriptionContains(String value) {
        List<Course> courses = courseRepository.findByDescriptionContains(value);
        if (courses.isEmpty()){
            throw new NotFoundException("Sorry Course with" + value + "tittle is not found");
        }
        return courses;
    }

    @Override
    public List<Course> getBy(String keyword, String value)  {
        switch (keyword){
            case "title":
                return findByTitleContains(value);
            case "description":
                return findByDescriptionContains(value);
            default:
                return courseRepository.findAll();
        }
    }

    @Override
    public Course create(Course course) throws Exception {
        try {
            Optional<CourseType> courseType = courseTypeRepository.findById(course.getCourseId().)
            if (courseType.isEmpty()) {
                throw new NotFoundException("No course type");
            }

            course.c(courseType.get());
            Course newCourse = courseRepository.save(course);
            return newCourse;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
    }

    @Override
    public Course get(String id) throws Exception {
        return null;
    }

    @Override
    public void update(Course course, String id) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }
}
