package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Course;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.TeacherCourse;
import com.gwy.manager.mapper.CourseMapper;
import com.gwy.manager.mapper.TeacherCourseMapper;
import com.gwy.manager.mapper.TeacherMapper;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.service.TeacherCourseService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.DateUtilCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public int addTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseMapper.insert(teacherCourse);
    }

    @Override
    public int changeTeacherCourseScore(String tcId, Integer changeScore) {
        return teacherCourseMapper.updateAppraiseScore(tcId, changeScore);
    }

    @Override
    public int addTeacherCoursesBatch(List<TeacherCourse> teacherCourses) {
        return teacherCourseMapper.insertBatch(teacherCourses);
    }

    @Override
    public ResultVO getCoursesByTeacherAndTerm(String teacherNo) {

        ResultVO resultVO = new ResultVO();

        //获得当前学期
        String currentTerm = termMapper.getCurrentTerm(DateUtilCustom.getDate());
        try {
            List<TeacherCourse> courses = teacherCourseMapper
                    .getTeacherCoursesByTeacherNoAndTermId(teacherNo, currentTerm);

            if (courses.size() == 0) {
                resultVO.setData(ResponseDataMsg.NotFound.getMsg());
            } else {
                resultVO.success(courses);
            }
        } catch (Exception e) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO getCoursesByStudentAndTerm(String studentNo, String termId) {

        ResultVO resultVO = new ResultVO();

        List<TeacherCourse> teacherCourses = teacherCourseMapper
                .getTeacherCourseByStudentNoAndTermId(studentNo, termId);

        if (teacherCourses.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            Collection<Map<String, Object>> maps = BeanUtil.beansToList(teacherCourses);

            List<String> courseNos = new ArrayList<>();
            List<String> teacherNos = new ArrayList<>();

            for (Map<String, Object> map : maps) {
                //先将教师号和课程号添加至列表以便查找
                courseNos.add((String) map.get("courseNo"));
                teacherNos.add((String) map.get("teacherNo"));

                //将上课时间格式化
                String allTime = (String) map.get("time");
                String[] times = allTime.split(",");

                Map<String, Object> timeMap = new LinkedHashMap<>();
                int i = 0;
                for (String time : times) {
                    Map<String, String> classes = new LinkedHashMap<>();

                    String[] s = time.split("_");
                    classes.put("day", s[0]);

                    String[] numClass = s[1].split("-");
                    classes.put("clock", "第" + numClass[0] + "节-第" + numClass[1] + "节");

                    timeMap.put("第" + (++i) + "次", classes);
                }

                map.put("time", timeMap);
            }

            List<Course> courses = courseMapper.getCoursesByIds(courseNos);
            List<String> teacherNames = teacherMapper.getTeachersByIds(teacherNos);

            int i = 0;
            for (Map<String, Object> map : maps) {
                map.put("courseName", courses.get(i).getCourseName());
                map.put("courseHour", courses.get(i).getHour());
                map.put("credit", courses.get(i).getCredit());
                map.put("teacherName", teacherNames.get(i));

                i++;
            }
            resultVO.success(maps);
        }

        return resultVO;
    }
}
