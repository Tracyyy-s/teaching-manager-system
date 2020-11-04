package com.gwy.manager.mapper;

import com.gwy.manager.entity.TermTarget;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TermTargetMapper {
    int deleteByPrimaryKey(@Param("termId") String termId, @Param("targetId") Integer targetId);

    int insert(TermTarget record);

    TermTarget selectByPrimaryKey(@Param("termId") String termId, @Param("targetId") Integer targetId);

    List<TermTarget> selectAll();

    int updateByPrimaryKey(TermTarget record);

    /**
     * 添加本学期的所有评价指标
     * @param termTargets
     * @return
     */
    int insertTermTargets(@Param("termTargets") List<TermTarget> termTargets);

    /**
     * 获得学期所有学生评价指标
     * @param termId
     * @return
     */
    List<Integer> getStudentTermTargets(String termId);

    /**
     * 获得学期所有教师评价指标
     * @param termId
     * @return
     */
    List<Integer> getTeacherTermTargets(String termId);
}