package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.Target;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface TargetMapper {
    int deleteByPrimaryKey(Integer targetId);

    int insert(Target record);

    Target selectByPrimaryKey(Integer targetId);

    List<Target> selectAll();

    int updateByPrimaryKey(Target record);

    /**
     * 获得学生评价的指标
     * @return  结果集
     */
    List<Target> getStudentTargets();

    /**
     * 获得教师评价的指标
     * @return  结果集
     */
    List<Target> getTeacherTargets();

    /**
     * 获得指标列表的指标
     * @return  结果集
     */
    List<Target> getTargetsByIds(@Param("ids") List<Integer> ids);
}