package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherAssess;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TeacherAssessMapper {

    int insert(TeacherAssess record);

    List<TeacherAssess> selectAll();

    int updateByPrimaryKey(TeacherAssess record);

    /**
     * 删除教师提交的评价
     * @param teacherNo
     * @param assessedTeacherNo
     * @param termId
     * @return
     */
    int deleteByPrimaryKey(@Param("teacherNo") String teacherNo,
                           @Param("assessedTeacherNo") String assessedTeacherNo,
                           @Param("termId") String termId);

    /**
     * 获取某学院的教师在某学期的评价列表
     * @param deptId
     * @param termId
     * @return
     */
    List<TeacherAssess> getTeacherAssessesByDeptAndTerm(@Param("deptId") String deptId,
                                                        @Param("termId") String termId);

    /**
     * 获得某教师在某学期对某个教师的评价
     * @param teacherNo
     * @param assessedTeacherNo
     * @param termId
     * @return
     */
    TeacherAssess selectByPrimaryKey(@Param("teacherNo") String teacherNo,
                                     @Param("assessedTeacherNo") String assessedTeacherNo,
                                     @Param("termId") String termId);

}