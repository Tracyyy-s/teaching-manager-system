package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.entity.Term;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.service.TermService;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@CacheConfig(cacheNames = "terms")
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

    @Cacheable(keyGenerator = "allTerms")
    @Override
    public ResultVo getTerms() {
        ResultVo resultVO;

        List<Term> terms;
        try {
            terms = termMapper.selectAll();
            resultVO = ResultVoUtil.success(terms);
        } catch (Exception e) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        return resultVO;
    }

    @Override
    public int addTermsByBatch(List<Term> terms) {
        return 0;
    }

    @Cacheable(keyGenerator = "currentTerm")
    @Override
    public ResultVo getCurrentTerm() {
        return ResultVoUtil.success(termMapper.getCurrentTerm(DateUtilCustom.getDate()));
    }
}
