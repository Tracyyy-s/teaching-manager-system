package com.gwy.manager.service.impl;

import com.gwy.manager.domain.entity.Major;
import com.gwy.manager.mapper.MajorMapper;
import com.gwy.manager.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:10
 */
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public int addMajor(Major major) {
        return majorMapper.insert(major);
    }

    @Override
    public int updateMajor(Major major) {
        return majorMapper.updateByPrimaryKey(major);
    }

    @Override
    public Major getMajorById(String majorId) {
        return majorMapper.selectByPrimaryKey(majorId);
    }

    @Override
    public List<Major> getMajorsByDept(String deptId) {
        return majorMapper.selectByDept(deptId);
    }
}
