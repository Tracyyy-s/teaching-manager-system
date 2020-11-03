package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Term;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermMapper termMapper;

    @Override
    public int addTerm(Term term) {
        return termMapper.insert(term);
    }

    @Override
    public int updateTerm(Term term) {
        return termMapper.updateByPrimaryKey(term);
    }

    @Override
    public Term getTerm(String termId) {
        return termMapper.selectByPrimaryKey(termId);
    }

    @Override
    public List<Term> getTerms() {
        return termMapper.selectAll();
    }

    @Override
    public int addTermsByBatch(List<Term> terms) {
        return 0;
    }
}
