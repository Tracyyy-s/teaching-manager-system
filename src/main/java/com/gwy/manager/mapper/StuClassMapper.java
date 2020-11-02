package com.gwy.manager.mapper;

import com.gwy.manager.entity.StuClass;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface StuClassMapper {

    /**
     * 删除班级
     * @param classId 班级id
     * @return  影响行数
     */
    int deleteByPrimaryKey(String classId);

    /**
     * 插入班级
     * @param record 将插入的班级
     * @return  影响行数
     */
    int insert(StuClass record);

    /**
     * 查找班级
     * @param classId 专班级id
     * @return  查询结果
     */
    StuClass selectByPrimaryKey(String classId);

    /**
     * 所有班级
     * @return  返回所有班级
     */
    List<StuClass> selectAll();

    /**
     * 修改班级信息
     * @param record 将修改的班级
     * @return  修改行数
     */
    int updateByPrimaryKey(StuClass record);
}