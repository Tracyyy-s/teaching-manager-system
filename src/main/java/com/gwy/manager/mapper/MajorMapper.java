package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.Major;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface MajorMapper {
    int deleteByPrimaryKey(String majorId);

    int insert(Major record);

    Major selectByPrimaryKey(String majorId);

    List<Major> selectAll();

    int updateByPrimaryKey(Major record);

    /**
     * 获得某学院的所有专业
     * @param deptId    学院id
     * @return  返回结果
     */
    List<Major> selectByDept(String deptId);
}