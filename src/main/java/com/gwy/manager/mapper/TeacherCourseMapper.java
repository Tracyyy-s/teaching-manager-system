package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherCourse;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface TeacherCourseMapper {

    /**
     * 删除教师-课程信息
     * @param tcId 教师课程-tcId
     * @return  影响行数
     */
    int deleteByPrimaryKey(String tcId);

    /**
     * 添加教师-课程
     * @param record 预添加
     * @return  影响行数
     */
    int insert(TeacherCourse record);

    /**
     * 查询教师-课程信息
     * @param tcId 教师-课程tdId
     * @return  查询结果
     */
    TeacherCourse selectByPrimaryKey(String tcId);

    /**
     * 所有教师-课程信息
     * @return  所有查询结果
     */
    List<TeacherCourse> selectAll();

    /**
     * 更新教师-课程信息
     * @param record 预修改
     * @return  修改行数
     */
    int updateByPrimaryKey(TeacherCourse record);
}