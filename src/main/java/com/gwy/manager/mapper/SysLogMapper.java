package com.gwy.manager.mapper;

import com.gwy.manager.entity.SysLog;
import java.util.List;

public interface SysLogMapper {

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    List<SysLog> selectAll();

}