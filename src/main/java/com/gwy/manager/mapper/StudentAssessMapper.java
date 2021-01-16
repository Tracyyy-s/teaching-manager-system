package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.StudentAssess;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface StudentAssessMapper {
    int deleteByPrimaryKey(@Param("studentNo") String studentNo, @Param("tcId") String tcId);

    int insert(StudentAssess record);

    StudentAssess selectByPrimaryKey(@Param("studentNo") String studentNo, @Param("tcId") String tcId);

    List<StudentAssess> selectAll();

    int updateByPrimaryKey(StudentAssess record);

    /**
     * 获取学生在本学期的评价列表
     * @param studentNo 学号
     * @param termId    学期号
     * @return  结果集
     */
    List<StudentAssess> selectByStudentNoAndTerm(@Param("studentNo") String studentNo,
                                               @Param("termId") String termId);

    /**
     * 获得某门课的评价
     * @param tcId  课程号
     * @return  结果集
     */
    List<StudentAssess> selectByTcId(String tcId);

    /**
     * 获得学生对课程评价状态
     * @param studentNo 学号
     * @param tcIds 教师课程号
     * @return  结果集
     */
    List<Integer> selectStateByStudentAndTcIds(@Param("studentNo") String studentNo,
                                               @Param("tcIds") List<String> tcIds);

    /**
     * 获得某学期教师所有的课程评价
     * @param teacherNos    教师id列表
     * @param termId        学期id
     * @return  结果集
     */
    List<Map<String, Object>> selectByTeacherNosAndTerm(@Param("teacherNos") List<String> teacherNos,
                                                        @Param("termId") String termId);
}