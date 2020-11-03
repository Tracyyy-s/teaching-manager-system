package com.gwy.manager.service;

import com.gwy.manager.entity.Target;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:01
 */
public interface TargetService {

    /**
     * 添加指标
     * @param target 预添加
     * @return  影响行数
     */
    int addTarget(Target target);

    /**
     * 获得学生评价的指标
     * @return
     */
    List<Target> getStudentTargets();

    /**
     * 获得教师评价的指标
     * @return
     */
    List<Target> getTeacherTargets();

    /**
     * 修改指标
     * @param target 修改指标
     * @return  影响行数
     */
    int updateTarget(Target target);

    /**
     * 删除指标
     * @param targetId 指标id
     * @return  影响行数
     */
    int deleteTarget(Integer targetId);
}
