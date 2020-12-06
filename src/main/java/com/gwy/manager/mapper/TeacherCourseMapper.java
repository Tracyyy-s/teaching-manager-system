package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface TeacherCourseMapper {

    int deleteByPrimaryKey(String tcId);

    int insert(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(String tcId);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse record);

    /**
     * 修改学生评价分数
     * @param tcId  教师课程id
     * @param changeScore   修改的分数
     * @return  返回结果
     */
    int updateAppraiseScore(@Param("tcId") String tcId,
                            @Param("changeScore")Integer changeScore);

    /**
     * 获得教师在某学期教授的全部课程
     * @param teacherNo 教师id
     * @param termId    学期id
     * @return  返回结果
     */
    List<TeacherCourse> selectByTeacherNoAndTermId(@Param("teacherNo") String teacherNo,
                                                              @Param("termId") String termId);

    /**
     * 获得学生在某学期学习的全部课程
     * @param studentNo 学号
     * @param termId    学期
     * @return  返回结果
     */
    List<TeacherCourse> selectByStudentNoAndTermId(@Param("studentNo") String studentNo,
                                                             @Param("termId") String termId);

    /**
     * 获得某学院某学期开设的所有课程
     * @param deptId    学院id
     * @param termId    学期id
     * @return  结果集
     */
    List<TeacherCourse> selectByTermAndDept(@Param("deptId") String deptId,
                                            @Param("termId") String termId);

    /**
     * 批量添加教师-课程信息
     * @param teacherCourses    预添加
     * @return  结果集
     */
    int insertBatch(@Param("teacherCourses") List<TeacherCourse> teacherCourses);
}