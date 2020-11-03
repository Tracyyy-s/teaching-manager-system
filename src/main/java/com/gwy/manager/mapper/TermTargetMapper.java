package com.gwy.manager.mapper;

import com.gwy.manager.entity.TermTarget;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TermTargetMapper {
    int deleteByPrimaryKey(@Param("termId") Integer termId, @Param("targetId") Integer targetId);

    int insert(TermTarget record);

    TermTarget selectByPrimaryKey(@Param("termId") Integer termId, @Param("targetId") Integer targetId);

    List<TermTarget> selectAll();

    int updateByPrimaryKey(TermTarget record);
}