package com.gwy.manager.mapper;

import com.gwy.manager.entity.Major;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface MajorMapper {

    /**
     * 删除专业
     * @param majorId 专业id
     * @return  影响行数
     */
    int deleteByPrimaryKey(String majorId);

    /**
     * 插入专业
     * @param record 将插入的专业
     * @return  影响行数
     */
    int insert(Major record);

    /**
     * 查找相关专业
     * @param majorId 专业id
     * @return  查询结果
     */
    Major selectByPrimaryKey(String majorId);

    /**
     * 所有专业
     * @return  返回所有专业
     */
    List<Major> selectAll();

    /**
     * 修改专业信息
     * @param record 将修改的专业
     * @return  修改行数
     */
    int updateByPrimaryKey(Major record);
}