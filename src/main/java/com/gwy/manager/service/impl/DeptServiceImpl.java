package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Dept;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tracy
 * @date 2020/11/1 23:09
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public int addDept(Dept dept) {
        return deptMapper.insert(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public Dept getDeptById(String deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }
}
