package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.entity.Dept;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.service.DeptService;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:09
 */
@CacheConfig(cacheNames = "depts")
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

    @Cacheable(key = "#deptId")
    @Override
    public ResultVo getDeptById(String deptId) {

        ResultVo resultVO;

        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(dept);
        }
        return resultVO;
    }

    @Override
    public Dept getDeptByName(String name) {
        return deptMapper.getDeptByName(name);
    }

    @Cacheable(key = "'all'")
    @Override
    public ResultVo getAllDepts() {

        ResultVo resultVO;

        List<Dept> depts = deptMapper.selectAll();
        if (depts.size() == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(depts);
        }

        return resultVO;
    }
}
