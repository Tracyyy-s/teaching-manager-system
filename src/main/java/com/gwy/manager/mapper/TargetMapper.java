package com.gwy.manager.mapper;

import com.gwy.manager.entity.Target;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface TargetMapper {

    /**
     * 删除评价指标
     * @param targetId 指标id
     * @return  影响行数
     */
    int deleteByPrimaryKey(String targetId);

    /**
     * 添加指标
     * @param record 预添加的指标
     * @return  影响行数
     */
    int insert(Target record);

    /**
     * 获取指标
     * @param targetId 指标id
     * @return  查询结果
     */
    Target selectByPrimaryKey(String targetId);

    /**
     * 获取所有指标
     * @return  返回所有评价指标
     */
    List<Target> selectAll();

    /**
     * 更新指标信息
     * @param record 预更新
     * @return  修改行数
     */
    int updateByPrimaryKey(Target record);
}