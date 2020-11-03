package com.gwy.manager.service;

import com.gwy.manager.entity.Term;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:02
 */
public interface TermService {

    /**
     * 添加学期信息
     * @param term 预添加的学期
     * @return  影响行数
     */
    int addTerm(Term term);

    /**
     * 修改学期信息
     * @param term 预修改的学期
     * @return  影响行数
     */
    int updateTerm(Term term);

    /**
     * 获得单个学期信息
     * @param termId 学期id
     * @return  查询结果
     */
    Term getTerm(String termId);

    /**
     * 获得所有学期的信息
     * @return
     */
    List<Term> getTerms();

    /**
     * 批量导入学期
     * @param terms 预导入的学期
     * @return  影响行数
     */
    int addTermsByBatch(List<Term> terms);
}
