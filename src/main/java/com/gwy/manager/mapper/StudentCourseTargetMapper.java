package com.gwy.manager.mapper;

import com.gwy.manager.entity.StudentCourseTarget;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentCourseTargetMapper {
    int deleteByPrimaryKey(@Param("studentNo") String studentNo, @Param("tcId") String tcId);

    int insert(StudentCourseTarget record);

    StudentCourseTarget selectByPrimaryKey(@Param("studentNo") String studentNo, @Param("tcId") String tcId);

    List<StudentCourseTarget> selectAll();

    int updateByPrimaryKey(StudentCourseTarget record);
}