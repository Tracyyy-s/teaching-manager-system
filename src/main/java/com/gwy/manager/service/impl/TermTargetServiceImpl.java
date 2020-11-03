package com.gwy.manager.service.impl;

import com.gwy.manager.entity.TermTarget;
import com.gwy.manager.mapper.TermTargetMapper;
import com.gwy.manager.service.TermTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 16:18
 */
@Service
public class TermTargetServiceImpl implements TermTargetService {

    @Autowired
    private TermTargetMapper termTargetMapper;

    @Override
    public int addTermTargets(List<TermTarget> termTargets) {
        return termTargetMapper.insertTermTargets(termTargets);
    }
}
