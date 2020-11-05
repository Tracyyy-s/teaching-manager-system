package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.dto.UserOption;
import com.gwy.manager.entity.Target;
import com.gwy.manager.mapper.TargetMapper;
import com.gwy.manager.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResultVO addTarget(Target target, Integer targetType) {

        ResultVO resultVO = new ResultVO();

        //没有匹配的指标类型
        if (!targetType.equals(UserOption.STUDENT.getTargetType()) &&
                !targetType.equals(UserOption.TEACHER.getTargetType())) {
            resultVO.setData("Not That Target Type");
            return resultVO;
        }

        int res = targetMapper.insert(target);
        if (res == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    /**
     * 通过传递参数获取教师/学生的评价列表
     * @param userType   教师/学生
     * @return  结果集
     */
    @Override
    public ResultVO getTargets(String userType) {

        ResultVO resultVO = new ResultVO();

        List<Target> targetList;

        //既不是教师对象也不是学生对象
        if (!UserOption.STUDENT.getUserType().equals(userType) &&
                !UserOption.TEACHER.getUserType().equals(userType)) {
            resultVO.setData("Not That User");
            return resultVO;
        } else if (UserOption.STUDENT.getUserType().equals(userType)) {
            targetList = targetMapper.getStudentTargets();
        } else {
            targetList = targetMapper.getTeacherTargets();
        }

        //未找到任何指标
        if (targetList.size() == 0) {
            resultVO.setData("Not Found");
        } else {
            resultVO.success(targetList);
        }

        return resultVO;
    }

    @Override
    public ResultVO updateTarget(Target target) {

        ResultVO resultVO = new ResultVO();

        int i = targetMapper.updateByPrimaryKey(target);
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public List<Target> getTargetsByIds(List<Integer> ids) {
        return targetMapper.getTargetsByIds(ids);
    }

    @Override
    public ResultVO deleteTarget(Integer targetId) {

        ResultVO resultVO = new ResultVO();

        int i = targetMapper.deleteByPrimaryKey(targetId);
        if (i == 0) {
            resultVO.setData("Not Found");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }
}
