package com.gwy.manager.mapper;

import com.gwy.manager.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminMapper {
    int deleteByPrimaryKey(String adminNo);

    int insert(Admin record);

    Admin selectByPrimaryKey(String adminNo);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

    int updatePassword(@Param("adminNo") String adminNo, @Param("password") String password);

    String selectMaxAdminNo();

    int updateDeptIds(@Param("adminNo") String adminNo, @Param("deptIds") String deptIds);
}