package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherAssess;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherAssessMapper {
    int deleteByPrimaryKey(@Param("teacherNo") String teacherNo, @Param("tcId") String tcId);

    int insert(TeacherAssess record);

    TeacherAssess selectByPrimaryKey(@Param("teacherNo") String teacherNo, @Param("tcId") String tcId);

    List<TeacherAssess> selectAll();

    int updateByPrimaryKey(TeacherAssess record);
}