package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Dept;

/**
 * @author Tracy
 * @date 2020/11/1 23:01
 */
public interface DeptService {

    /**
     * 添加学院信息
     * @param dept  预添加
     * @return  结果集
     */
    int addDept(Dept dept);

    /**
     * 修改学院信息
     * @param dept  预修改
     * @return  结果集
     */
    int updateDept(Dept dept);

    /**
     * 获得指定学院
     * @param deptId 学院id
     * @return  结果集
     */
    Dept getDeptById(String deptId);

    /**
     * 通过学院名获取学院
     * @param name  学院名
     * @return  结果集
     */
    Dept getDeptByName(String name);

    /**
     * 获得所有学院
     * @return  结果集
     */
    ResultVO getAllDepts();
}
