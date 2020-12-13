package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherAssess;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface TeacherAssessMapper {

    int insert(TeacherAssess record);

    List<TeacherAssess> selectAll();

    int updateByPrimaryKey(TeacherAssess record);

    /**
     * 删除教师提交的评价
     * @param teacherNo 教师号
     * @param assessedTeacherNo 被评价的教师号
     * @param termId    学期id
     * @return  jieugoji
     */
    int deleteByPrimaryKey(@Param("teacherNo") String teacherNo,
                           @Param("assessedTeacherNo") String assessedTeacherNo,
                           @Param("termId") String termId);

    /**
     * 获取某学院的教师在某学期的评价列表
     * @param deptId    学院id
     * @param termId    学期id
     * @return  结果集
     */
    List<TeacherAssess> getTeacherAssessesByDeptAndTerm(@Param("deptId") String deptId,
                                                        @Param("termId") String termId);

    /**
     * 获得某教师在某学期对某个教师的评价
     * @param teacherNo 教师号
     * @param assessedTeacherNo 被评价教师号
     * @param termId    学期id
     * @return  结果集
     */
    TeacherAssess selectByPrimaryKey(@Param("teacherNo") String teacherNo,
                                     @Param("assessedTeacherNo") String assessedTeacherNo,
                                     @Param("termId") String termId);

    /**
     * 获得某教师的所有评价
     * @param teacherNo 教师id
     * @return  返回列表
     */
    List<TeacherAssess> selectAllByTno(String teacherNo);

    /**
     * 获得某教师在某学期的所有评价
     * @param teacherNo 教师id
     * @param assessedTeacherNos    被评价的id
     * @param termId    学期id
     * @return  被评价的教师id列表
     */
    List<String> judgeAssessed(@Param("teacherNo") String teacherNo,
                               @Param("assessedTeacherNos") List<String> assessedTeacherNos,
                               @Param("termId") String termId);

    /**
     * 获得某学院教师某学期所有的教师评价结果
     * @param deptId    学院id
     * @param termId    学期id
     * @return  结果集
     */
    List<Map<String, Object>> selectByTermAndDept(@Param("deptId") String deptId,
                                                  @Param("termId") String termId);
}