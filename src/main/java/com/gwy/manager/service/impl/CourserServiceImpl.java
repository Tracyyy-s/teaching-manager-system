package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Course;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.CourseMapper;
import com.gwy.manager.service.CourseService;
import org.apache.commons.collections4.CollectionUtils;
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

    @Override
    public ResultVO getCoursesOfTeacher(String teacherNo) {

        ResultVO resultVO = new ResultVO();

        List<Course> coursesOfTeacher = courseMapper.getCoursesOfTeacher(teacherNo);
        if (CollectionUtils.isEmpty(coursesOfTeacher)) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(coursesOfTeacher);
        }

        return resultVO;
    }
}
