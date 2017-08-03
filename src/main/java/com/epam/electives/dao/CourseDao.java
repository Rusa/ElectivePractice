package com.epam.electives.dao;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.UserProfile;

import java.util.List;

/**
 * Created by rusamaha on 7/19/17.
 */
public interface CourseDao {
    List<Course> findAll();

    Course findCourseById(Long id);

    void saveOrUpdate(Course news);

    PageDto<Course> findParts(GetEntityRequest request);

    List<Course> findN(int n);

    List<UserProfile> findStudentsByCourse(Course course);
}
