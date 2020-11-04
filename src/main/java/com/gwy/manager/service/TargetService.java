package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Target;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:01
 */
public interface TargetService {

    /**
     * 添加指标
     * @param target 预添加
     * @return  结果集
     */
    ResultVO addTarget(Target target);

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
     * 通过指标的ids获取指标
     * @param ids   ids
     * @return  查询结果
     */
    List<Target> getTargetsById(List<Integer> ids);

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
