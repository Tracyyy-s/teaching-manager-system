package com.gwy.manager.mapper;

import com.gwy.manager.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface TeacherMapper {

    /**
     * 删除教师
     * @param tno 教师tno
     * @return  影响行数
     */
    int deleteByPrimaryKey(String tno);

    /**
     * 添加教师
     * @param record 预添加的教师
     * @return  影响行数
     */
    int insert(Teacher record);

    /**
     * 查找教师
     * @param tno 教师tno
     * @return  影响行数
     */
    Teacher selectByPrimaryKey(String tno);

    /**
     * 所有教师
     * @return  返回所有教师
     */
    List<Teacher> selectAll();

    /**
     * 修改教师信息
     * @param record 预修改的教师
     * @return  修改行数
     */
    int updateByPrimaryKey(Teacher record);
}