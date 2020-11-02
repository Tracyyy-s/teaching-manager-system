package com.gwy.manager.mapper;

import com.gwy.manager.entity.TermTarget;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TermTargetMapper {
    int deleteByPrimaryKey(@Param("termId") String termId, @Param("targetId") String targetId);

    int insert(TermTarget record);

    TermTarget selectByPrimaryKey(@Param("termId") String termId, @Param("targetId") String targetId);

    List<TermTarget> selectAll();

    int updateByPrimaryKey(TermTarget record);
}