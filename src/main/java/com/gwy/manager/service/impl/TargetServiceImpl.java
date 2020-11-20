package com.gwy.manager.service.impl;

import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.entity.Target;
import com.gwy.manager.mapper.TargetMapper;
import com.gwy.manager.service.TargetService;
import com.gwy.manager.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        Integer targetType = target.getTargetObject();
        //没有匹配的指标类型
        if (!targetType.equals(UserOption.STUDENT.getTargetType()) &&
                !targetType.equals(UserOption.TEACHER.getTargetType())) {
            resultVO.setData("No That Target Type");
            return resultVO;
        }

        int res = targetMapper.insert(target);
        if (res == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
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
            resultVO.setData("No That User Type");
            return resultVO;
        } else if (UserOption.STUDENT.getUserType().equals(userType)) {
            targetList = targetMapper.getStudentTargets();
        } else {
            targetList = targetMapper.getTeacherTargets();
        }

        //未找到任何指标
        if (targetList.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {

            List<Object> list = new ArrayList<>();
            int i = 0;
            for (Target target : targetList) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("index", (++i));
                map.putAll(BeanUtil.beanToMap(target));

                list.add(map);
            }

            resultVO.success(list);
        }

        return resultVO;
    }

    @Override
    public ResultVO updateTarget(Target target) {

        ResultVO resultVO = new ResultVO();

        int i = targetMapper.updateByPrimaryKey(target);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
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
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }
}
