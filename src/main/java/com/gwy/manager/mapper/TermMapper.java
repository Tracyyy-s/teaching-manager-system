package com.gwy.manager.mapper;

import com.gwy.manager.entity.Term;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface TermMapper {
    int deleteByPrimaryKey(String termId);

    int insert(Term record);

    Term selectByPrimaryKey(String termId);

    List<Term> selectAll();

    int updateByPrimaryKey(Term record);

    int insertByBatch(@Param("terms") List<Term> terms);

    String getCurrentTerm(Date date);
}