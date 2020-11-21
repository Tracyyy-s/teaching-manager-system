package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Dept;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Dept getDeptByName(String name) {
        return deptMapper.getDeptByName(name);
    }

    @Override
    public ResultVO getAllDepts() {

        ResultVO resultVO = new ResultVO();

        List<Dept> depts = deptMapper.selectAll();
        if (depts.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(depts);
        }

        return resultVO;
    }
}
