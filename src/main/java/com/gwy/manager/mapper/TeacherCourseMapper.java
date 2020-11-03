package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherCourseMapper {

    int deleteByPrimaryKey(String tcId);

    int insert(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(String tcId);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse record);

    /**
     * 修改学生评价分数
     * @param tcId
     * @param changeScore
     * @return
     */
    int updateAppraiseScore(@Param("tcId") String tcId,
                            @Param("changeScore")Integer changeScore);

    /**
     * 获得教师在某学期教授的全部课程
     * @param teacherNo
     * @param termId
     * @return
     */
    List<TeacherCourse> getTeacherCoursesByTeacherNoAndTermId(@Param("teacherNo") String teacherNo,
                                                              @Param("termId") String termId);

    /**
     * 获得学生在某学期学习的全部课程
     * @param studentNo
     * @param termId
     * @return
     */
    List<TeacherCourse> getTeacherCourseByStudentNoAndTermId(@Param("studentNo") String studentNo,
                                                             @Param("termId") String termId);

    /**
     * 批量添加教师-课程信息
     * @param teacherCourses
     * @return
     */
    int insertBatch(@Param("teacherCourses") List<TeacherCourse> teacherCourses);
}