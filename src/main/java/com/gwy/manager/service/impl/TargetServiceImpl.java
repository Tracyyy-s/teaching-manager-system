package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.enums.UserOption;
import com.gwy.manager.domain.entity.Target;
import com.gwy.manager.mapper.TargetMapper;
import com.gwy.manager.service.TargetService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    public ResultVo addTarget(Target target) {

        ResultVo resultVO;

        Integer targetType = target.getTargetObject();
        //没有匹配的指标类型
        if (!targetType.equals(UserOption.STUDENT.getTargetType()) &&
                !targetType.equals(UserOption.TEACHER.getTargetType())) {
            resultVO = ResultVoUtil.error("No That Target Type");
            return resultVO;
        }

        int res = targetMapper.insert(target);
        if (res == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    /**
     * 通过传递参数获取教师/学生的评价列表
     * @param userType   教师/学生
     * @return  结果集
     */
    @Override
    public ResultVo getTargets(String userType) {

        ResultVo resultVO;

        List<Target> targetList;

        //既不是教师对象也不是学生对象
        if (!UserOption.STUDENT.getUserType().equals(userType) &&
                !UserOption.TEACHER.getUserType().equals(userType)) {
            resultVO = ResultVoUtil.error("No That User Type");
            return resultVO;
        } else if (UserOption.STUDENT.getUserType().equals(userType)) {
            targetList = targetMapper.getStudentTargets();
        } else {
            targetList = targetMapper.getTeacherTargets();
        }

        //未找到任何指标
        if (targetList.size() == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {

            List<Object> list = new ArrayList<>();
            int i = 0;
            for (Target target : targetList) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("index", (++i));
                map.putAll(BeanUtil.beanToMap(target));

                list.add(map);
            }

            resultVO = ResultVoUtil.success(list);
        }

        return resultVO;
    }

    @Override
    public ResultVo updateTarget(Target target) {

        ResultVo resultVO;

        int i = targetMapper.updateByPrimaryKey(target);
        if (i == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    public List<Target> getTargetsByIds(List<Integer> ids) {
        return targetMapper.getTargetsByIds(ids);
    }

    @Override
    public ResultVo deleteTarget(Integer targetId) {

        ResultVo resultVO;

        int i = targetMapper.deleteByPrimaryKey(targetId);
        if (i == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }
}
