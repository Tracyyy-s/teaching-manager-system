package com.gwy.manager.mapper;

import com.gwy.manager.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public interface SysLogMapper {

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    List<SysLog> selectAll();

    List<Map<String, Object>> selectDataExplainAndCount();

    List<SysLog> selectByType(String type);

    int deleteByPrimaryKeys(@Param("ids") List<Integer> ids);
}