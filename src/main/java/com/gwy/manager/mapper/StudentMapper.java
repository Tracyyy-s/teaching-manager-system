package com.gwy.manager.mapper;

import com.gwy.manager.entity.Student;
import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(String studentNo);

    int insert(Student record);

    Student selectByPrimaryKey(String studentNo);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);
}