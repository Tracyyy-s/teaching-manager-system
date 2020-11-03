package com.gwy.manager.mapper;

import com.gwy.manager.entity.StudentAssess;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentAssessMapper {
    int deleteByPrimaryKey(@Param("studentNo") String studentNo, @Param("tcId") String tcId);

    int insert(StudentAssess record);

    StudentAssess selectByPrimaryKey(@Param("studentNo") String studentNo, @Param("tcId") String tcId);

    List<StudentAssess> selectAll();

    int updateByPrimaryKey(StudentAssess record);
}