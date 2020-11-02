package com.gwy.manager.mapper;

import com.gwy.manager.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface StudentMapper {

    /**
     * 删除学生
     * @param sno 学生sno
     * @return  影响行数
     */
    int deleteByPrimaryKey(String sno);

    /**
     * 添加学生
     * @param record 预添加的学生
     * @return  影响行数
     */
    int insert(Student record);

    /**
     * 查找学生
     * @param sno 学生sno
     * @return  影响行数
     */
    Student selectByPrimaryKey(String sno);

    /**
     * 所有学生
     * @return  返回所有学生
     */
    List<Student> selectAll();

    /**
     * 修改学生信息
     * @param record 预修改的学生
     * @return  修改行数
     */
    int updateByPrimaryKey(Student record);
}