package com.gwy.manager.mapper;

import com.gwy.manager.entity.Dept;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface DeptMapper {

    /**
     * 删除院系
     * @param deptId 学院id
     * @return  影响行数
     */
    int deleteByPrimaryKey(String deptId);

    /**
     * 添加院系
     * @param record 将添加的学院
     * @return  影响行数
     */
    int insert(Dept record);

    /**
     * 查找学院
     * @param deptId 学院id
     * @return  查询结果
     */
    Dept selectByPrimaryKey(String deptId);

    /**
     * 查询所有学院
     * @return  表中所有学院
     */
    List<Dept> selectAll();

    /**
     * 更新学院
     * @param record 将更新的学院
     * @return  影响行数
     */
    int updateByPrimaryKey(Dept record);
}