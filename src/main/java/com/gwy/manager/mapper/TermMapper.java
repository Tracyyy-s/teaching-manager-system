package com.gwy.manager.mapper;

import com.gwy.manager.entity.Term;
import java.util.List;

public interface TermMapper {
    int deleteByPrimaryKey(Integer termId);

    int insert(Term record);

    Term selectByPrimaryKey(Integer termId);

    List<Term> selectAll();

    int updateByPrimaryKey(Term record);
}