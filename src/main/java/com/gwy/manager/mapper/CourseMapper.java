package com.gwy.manager.mapper;

import com.gwy.manager.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TRacy
 */
@Component
public interface CourseMapper {

    /**
     * 删除课程
     * @param cno 课程cno
     * @return  影响行数
     */
    int deleteByPrimaryKey(String cno);

    /**
     * 插入课程
     * @param record 将插入的课程
     * @return  影响行数
     */
    int insert(Course record);

    /**
     * 查找课程
     * @param cno 课程cno
     * @return  影响行数
     */
    Course selectByPrimaryKey(String cno);

    /**
     * 查找到所有课程
     * @return  表中所有课程
     */
    List<Course> selectAll();

    /**
     * 更新课程
     * @param record 将更新的课程
     * @return  影响行数
     */
    int updateByPrimaryKey(Course record);
}