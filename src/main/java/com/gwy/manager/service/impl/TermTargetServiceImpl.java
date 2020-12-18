package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Target;
import com.gwy.manager.entity.Term;
import com.gwy.manager.entity.TermTarget;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.mapper.TargetMapper;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.mapper.TermTargetMapper;
import com.gwy.manager.service.TermTargetService;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 16:18
 */
@CacheConfig(cacheNames = "termTargets")
@Service
public class TermTargetServiceImpl implements TermTargetService {

    @Autowired
    private TermTargetMapper termTargetMapper;

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private TargetMapper targetMapper;

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public ResultVO addTermTargets(List<TermTarget> termTargets) {

        int i;
        try {
            i = termTargetMapper.insertTermTargets(termTargets);
        } catch (Exception e) {
            return ResultVOUtil.error("Already Published");
        }
        if (i != termTargets.size()) {
            return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            return ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        }
    }

    /**
     * 获得指定对象的学期指标
     * @param termId 学期id
     * @param obj   对象（可为学生或教师）
     * @return  结果集
     */
    private ResultVO getObjectTermTargets(String termId, String obj) {

        ResultVO resultVO = new ResultVO();

        //获得今天日期
        Date today = DateUtilCustom.getDate();

        Term term = termMapper.selectByPrimaryKey(termId);
        if (term == null) {
            resultVO = ResultVOUtil.error("Term Not Exist");
        } else if (today.after(term.getBeginDate()) && today.before(term.getEndDate())) {

            //存储指标id的列表
            List<Integer> targetIds = new ArrayList<>();

            if (UserOption.STUDENT.getUserType().equals(obj)) {

                //获得学生学期评价指标列表
                targetIds = termTargetMapper.getStudentTermTargets(termId);
            } else if (UserOption.TEACHER.getUserType().equals(obj)) {

                //获得教师学期评价指标列表
                targetIds = termTargetMapper.getTeacherTermTargets(termId);
            }

            //判断本学期是否发布评价
            if (CollectionUtils.isEmpty(targetIds)) {
                resultVO = ResultVOUtil.error("Not Publish Yet");
            } else {
                //若发布则返回发布的评价
                List<Target> targets = targetMapper.getTargetsByIds(targetIds);
                resultVO = ResultVOUtil.success(targets);
                System.out.println(targets);
            }
        } else if (today.before(term.getBeginDate())) {
            resultVO = ResultVOUtil.error("Too Early");
        } else if (today.after(term.getEndDate())) {
            resultVO = ResultVOUtil.error("Too Late");
        }

        return resultVO;
    }

    @Cacheable(keyGenerator = "studentTermTargets")
    @Override
    public ResultVO getStudentTermTargets(String termId) {
        return this.getObjectTermTargets(termId, UserOption.STUDENT.getUserType());
    }

    @Cacheable(keyGenerator = "teacherTermTargets")
    @Override
    public ResultVO getTeacherTermTargets(String termId) {
        return this.getObjectTermTargets(termId, UserOption.TEACHER.getUserType());
    }
}
