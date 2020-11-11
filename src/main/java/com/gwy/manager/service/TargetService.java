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
     * 获得指定对象指标
     * @param userType  对象类型
     * @return  结果集
     */
    ResultVO getTargets(String userType);

    /**
     * 通过指标列表获取指标
     * @param ids   ids
     * @return  查询结果
     */
    List<Target> getTargetsByIds(List<Integer> ids);

    /**
     * 修改指标
     * @param target 修改指标
     * @return  影响行数
     */
    ResultVO updateTarget(Target target);

    /**
     * 删除指标
     * @param targetId 指标id
     * @return  影响行数
     */
    ResultVO deleteTarget(Integer targetId);
}
