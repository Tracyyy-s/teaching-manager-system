package com.gwy.manager.mapper;

import com.gwy.manager.entity.SysPermissionApi;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author Tracy
 * @date 2020/12/10 15:40
 */
@Component
public interface SysPermissionApiMapper {
    int deleteByPrimaryKey(@Param("permissionId") Integer permissionId, @Param("api") String api);

    int insert(SysPermissionApi record);

    List<SysPermissionApi> selectAll();

    /**
     * 获得指定api的权限id列表
     * @param api   api
     * @return  结果list
     */
    List<Integer> selectPermissionIdsByApi(String api);
}