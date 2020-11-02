package com.gwy.manager.controller.teacher;

import com.gwy.manager.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tracy
 * @date 2020/11/2 19:25
 */
@RestController
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;


}
