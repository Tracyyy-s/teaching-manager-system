package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.TermTarget;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 16:18
 */
public interface TermTargetService {

    /**
     * 添加学期评价指标
     * @param termTargets 添加的指标列表
     * @return  返回行数
     */
    int addTermTargets(List<TermTarget> termTargets);

    /**
     * 获得学生学期评价指标
     * @return 结果集
     */
    ResultVO getStudentTermTargets(String termId);

    /**
     * 获得教师评价指标
     * @param termId 学期id
     * @return  结果集
     */
    ResultVO getTeacherTermTargets(String termId);
}
