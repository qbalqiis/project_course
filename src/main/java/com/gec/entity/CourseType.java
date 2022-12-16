package com.gec.entity;

import javax.persistence.*;

@Entity
@Table(name = "mst_course_type")
public class CourseType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private String courseTypeId;
    @Column(name = "type_name", nullable = false, length = 150, unique = true)
    private String typeName;
    public String getCourseTypeId(){
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "courseTypeId='" + courseTypeId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
