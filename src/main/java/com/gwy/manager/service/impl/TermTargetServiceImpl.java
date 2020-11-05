package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.dto.UserOption;
import com.gwy.manager.entity.Target;
import com.gwy.manager.entity.Term;
import com.gwy.manager.entity.TermTarget;
import com.gwy.manager.mapper.TargetMapper;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.mapper.TermTargetMapper;
import com.gwy.manager.service.TermTargetService;
import com.gwy.manager.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 16:18
 */
@Service
public class TermTargetServiceImpl implements TermTargetService {

    private Logger logger = LoggerFactory.getLogger(TermTargetServiceImpl.class);

    @Autowired
    private TermTargetMapper termTargetMapper;

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private TargetMapper targetMapper;

    @Override
    public int addTermTargets(List<TermTarget> termTargets) {
        return termTargetMapper.insertTermTargets(termTargets);
    }

    private ResultVO getObjectTermTargets(String termId, String obj) {

        ResultVO resultVO = new ResultVO();

        //获得今天日期
        Date today = DateUtil.getDate();

        Term term = termMapper.selectByPrimaryKey(termId);
        if (term == null) {
            resultVO.setData("Term Not Exist");
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
            if (targetIds.size() == 0) {
                resultVO.setData("Not Publish Yet");
            } else {
                //若发布则返回发布的评价
                List<Target> targets = targetMapper.getTargetsByIds(targetIds);
                resultVO.success(targets);
            }
        } else if (today.before(term.getBeginDate())) {
            resultVO.setData("Too Early");
        } else if (today.after(term.getEndDate())) {
            resultVO.setData("Too Late");
        }

        return resultVO;
    }

    @Override
    public ResultVO getStudentTermTargets(String termId) {
        return this.getObjectTermTargets(termId, UserOption.STUDENT.getUserType());
    }

    @Override
    public ResultVO getTeacherTermTargets(String termId) {
        return this.getObjectTermTargets(termId, UserOption.TEACHER.getUserType());
    }
}
