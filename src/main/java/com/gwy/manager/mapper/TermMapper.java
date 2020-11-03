package com.gwy.manager.mapper;

import com.gwy.manager.entity.Term;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermMapper {
    int deleteByPrimaryKey(String termId);

    int insert(Term record);

    Term selectByPrimaryKey(String termId);

    List<Term> selectAll();

    int updateByPrimaryKey(Term record);

    int insertByBatch(@Param("terms") List<Term> terms);
}