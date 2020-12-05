package com.gwy.manager.mapper;

import com.gwy.manager.entity.SysLog;
import java.util.List;
import java.util.Map;

public interface SysLogMapper {

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    List<SysLog> selectAll();

    List<Map<String, Object>> selectDataExplainAndCount();

    List<SysLog> selectByType(String type);
}