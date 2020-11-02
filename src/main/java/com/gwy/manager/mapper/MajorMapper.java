package com.gwy.manager.mapper;

import com.gwy.manager.entity.Major;
import java.util.List;

/**
 * @author TRacy
 */
public interface MajorMapper {
    int deleteByPrimaryKey(String majorId);

    int insert(Major record);

    Major selectByPrimaryKey(String majorId);

    List<Major> selectAll();

    int updateByPrimaryKey(Major record);
}