package com.gwy.manager.mapper;

import com.gwy.manager.entity.Dept;
import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(Dept record);

    Dept selectByPrimaryKey(String deptId);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept record);
}