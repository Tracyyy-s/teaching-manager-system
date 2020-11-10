package com.gwy.manager.mapper;

import com.gwy.manager.entity.Root;
import org.apache.ibatis.annotations.Param;

public interface RootMapper {

    Root getRoot();

    int updatePassword(@Param("password") String password);
}