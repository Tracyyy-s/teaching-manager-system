package com.gwy.manager.mapper;

import com.gwy.manager.entity.StuClass;
import java.util.List;

public interface StuClassMapper {
    int deleteByPrimaryKey(String classId);

    int insert(StuClass record);

    StuClass selectByPrimaryKey(String classId);

    List<StuClass> selectAll();

    int updateByPrimaryKey(StuClass record);
}