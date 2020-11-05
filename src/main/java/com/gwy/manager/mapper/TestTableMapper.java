package com.gwy.manager.mapper;

import com.gwy.manager.entity.TestTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestTableMapper {
    int insert(TestTable record);

    List<TestTable> selectAll();
}