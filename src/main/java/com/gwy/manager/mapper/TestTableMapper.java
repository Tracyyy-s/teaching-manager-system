package com.gwy.manager.mapper;

import com.gwy.manager.entity.TestTable;
import java.util.List;

public interface TestTableMapper {
    int insert(TestTable record);

    List<TestTable> selectAll();
}