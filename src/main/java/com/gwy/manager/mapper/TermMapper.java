package com.gwy.manager.mapper;

import com.gwy.manager.entity.Term;
import java.util.List;

/**
 * @author TRacy
 */
public interface TermMapper {
    int deleteByPrimaryKey(String termId);

    int insert(Term record);

    Term selectByPrimaryKey(String termId);

    List<Term> selectAll();

    int updateByPrimaryKey(Term record);
}