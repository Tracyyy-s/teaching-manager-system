package com.gwy.manager.mapper;

import com.gwy.manager.entity.StudentClass;
import java.util.List;

/**
 * @author TRacy
 */
public interface StudentClassMapper {
    int deleteByPrimaryKey(String classId);

    int insert(StudentClass record);

    StudentClass selectByPrimaryKey(String classId);

    List<StudentClass> selectAll();

    int updateByPrimaryKey(StudentClass record);
}