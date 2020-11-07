package com.gwy.manager.service;

import com.gwy.manager.entity.Dept;

/**
 * @author Tracy
 * @date 2020/11/1 23:01
 */
public interface DeptService {

    /**
     * 添加学院信息
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 修改学院信息
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 获得指定学院
     * @param deptId
     * @return
     */
    Dept getDeptById(String deptId);

    /**
     * 通过学院名获取学院
     * @param name
     * @return
     */
    Dept getDeptByName(String name);
}
