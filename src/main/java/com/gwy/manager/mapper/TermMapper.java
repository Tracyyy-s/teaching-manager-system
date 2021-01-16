package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.Term;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface TermMapper {
    int deleteByPrimaryKey(String termId);

    int insert(Term record);

    Term selectByPrimaryKey(String termId);

    List<Term> selectAll();

    int updateByPrimaryKey(Term record);

    int insertByBatch(@Param("terms") List<Term> terms);

    Term getCurrentTerm(Date date);
}