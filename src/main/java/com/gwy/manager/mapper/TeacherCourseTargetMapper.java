package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherCourseTarget;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author TRacy
 */
public interface TeacherCourseTargetMapper {
    int deleteByPrimaryKey(@Param("teacherNo") String teacherNo, @Param("tcId") String tcId);

    int insert(TeacherCourseTarget record);

    TeacherCourseTarget selectByPrimaryKey(@Param("teacherNo") String teacherNo, @Param("tcId") String tcId);

    List<TeacherCourseTarget> selectAll();

    int updateByPrimaryKey(TeacherCourseTarget record);
}