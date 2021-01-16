package com.gwy.manager.service;

import com.gwy.manager.domain.entity.Major;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:01
 */
public interface MajorService {

    /**
     * 添加专业信息
     * @param major 预添加
     * @return  影响行数
     */
    int addMajor(Major major);

    /**
     * 修改专业信息
     * @param major
     * @return
     */
    int updateMajor(Major major);

    /**
     * 获得专业信息
     * @param majorId
     * @return
     */
    Major getMajorById(String majorId);

    /**
     * 获得某学院的所有专业
     * @param deptId
     * @return
     */
    List<Major> getMajorsByDept(String deptId);
}
