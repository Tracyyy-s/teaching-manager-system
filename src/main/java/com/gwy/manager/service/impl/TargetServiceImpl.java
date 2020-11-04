package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Target;
import com.gwy.manager.mapper.TargetMapper;
import com.gwy.manager.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:12
 */
@Service
public class TargetServiceImpl implements TargetService {

    @Autowired
    private TargetMapper targetMapper;

    @Override
    public ResultVO addTarget(Target target) {

        ResultVO resultVO = new ResultVO();

        int res = targetMapper.insert(target);
        if (res == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.setData("Success");
        }

        return resultVO;
    }

    @Override
    public List<Target> getStudentTargets() {
        return targetMapper.getStudentTargets();
    }

    @Override
    public List<Target> getTeacherTargets() {
        return targetMapper.getTeacherTargets();
    }

    @Override
    public int updateTarget(Target target) {
        return targetMapper.updateByPrimaryKey(target);
    }

    @Override
    public List<Target> getTargetsById(List<Integer> ids) {
        return targetMapper.getTargetsByIds(ids);
    }

    @Override
    public int deleteTarget(Integer targetId) {
        return targetMapper.deleteByPrimaryKey(targetId);
    }
}
