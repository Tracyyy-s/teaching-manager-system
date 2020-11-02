package com.gwy.manager.controller.student;

import com.gwy.manager.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tracy
 * @date 2020/11/2 19:24
 */
@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;


}
