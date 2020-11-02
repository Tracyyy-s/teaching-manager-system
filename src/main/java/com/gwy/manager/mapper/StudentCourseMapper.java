package com.gwy.manager.mapper;

import com.gwy.manager.entity.StudentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author TRacy
 */
@Component
public interface StudentCourseMapper {

    /**
     * 删除学生-课程
     * @param sno 学生sno
     * @param tcId 教师课程 tcId
     * @return  影响行数
     */
    int deleteByPrimaryKey(@Param("sno") String sno, @Param("tcId") String tcId);

    /**
     * 插入学生-课程关系
     * @param record 预插入
     * @return  影响行数
     */
    int insert(StudentCourse record);

    /**
     * 查询学生-课程
     * @param sno 学生sno
     * @param tcId 教师-课程tcId
     * @return  返回结果
     */
    StudentCourse selectByPrimaryKey(@Param("sno") String sno, @Param("tcId") String tcId);

    /**
     * 查询所有学生-课程
     * @return  所有数据
     */
    List<StudentCourse> selectAll();

    /**
     * 更新数据
     * @param record 预更新
     * @return  影响行数
     */
    int updateByPrimaryKey(StudentCourse record);
}