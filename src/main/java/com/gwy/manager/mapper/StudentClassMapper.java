package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.StudentClass;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface StudentClassMapper {
    int deleteByPrimaryKey(String classId);

    int insert(StudentClass record);

    StudentClass selectByPrimaryKey(String classId);

    List<StudentClass> selectAll();

    int updateByPrimaryKey(StudentClass record);

    List<StudentClass> selectByDept(String deptId);
}