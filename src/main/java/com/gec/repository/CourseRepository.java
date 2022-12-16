package com.gec.repository;

import com.gec.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    @Query("SELECT c FROM Course c WHERE c.tittle LIKE %?1%")
    List<Course> findByTitleContains(String tittle);
    @Query("SELECT c FROM Course c WHERE c.description LIKE %?1%")
    List<Course> findByDescriptionContains(String description);
}
