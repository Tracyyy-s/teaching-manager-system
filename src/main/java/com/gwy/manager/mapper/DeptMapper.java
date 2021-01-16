package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.Dept;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface DeptMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(Dept record);

    Dept selectByPrimaryKey(String deptId);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept record);

    Dept getDeptByName(String name);

    @MapKey("deptId")
    Map<String, Dept> getDeptByIds(@Param("ids") List<String> ids);
}