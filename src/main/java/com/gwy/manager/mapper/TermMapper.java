package com.gwy.manager.mapper;

import com.gwy.manager.entity.Term;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface TermMapper {

    /**
     * 删除学期
     * @param termId 学期id
     * @return  影响行数
     */
    int deleteByPrimaryKey(String termId);

    /**
     * 添加学期
     * @param record 预添加的学期
     * @return  影响行数
     */
    int insert(Term record);

    /**
     * 查找学期
     * @param termId 学期id
     * @return  影响行数
     */
    Term selectByPrimaryKey(String termId);

    /**
     * 所有学期
     * @return  返回所有学期
     */
    List<Term> selectAll();

    /**
     * 修改学期信息
     * @param record 预修改的学期
     * @return  修改行数
     */
    int updateByPrimaryKey(Term record);
}