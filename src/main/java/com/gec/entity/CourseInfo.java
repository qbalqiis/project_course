package com.gec.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_course_info")
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String courseInfoId;
    @Column(name = "level", nullable = false, length = 30)
    private String level;
    @Column(name = "duration", nullable = false, length = 30)
    private String duration;

    public String getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(String courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseInfoId='" + courseInfoId + '\'' +
                ", level='" + level + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
