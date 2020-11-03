package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Course;
import com.gwy.manager.mapper.CourseMapper;
import com.gwy.manager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:09
 */
@Service
public class CourserServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course getCourse(String courseNo) {
        return courseMapper.selectByPrimaryKey(courseNo);
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateByPrimaryKey(course);
    }


}
