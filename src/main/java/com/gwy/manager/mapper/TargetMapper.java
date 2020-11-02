package com.gwy.manager.mapper;

import com.gwy.manager.entity.Target;
import java.util.List;

/**
 * @author TRacy
 */
public interface TargetMapper {
    int deleteByPrimaryKey(String targetId);

    int insert(Target record);

    Target selectByPrimaryKey(String targetId);

    List<Target> selectAll();

    int updateByPrimaryKey(Target record);
}