package com.gwy.manager.controller.student;

import com.gwy.manager.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/2 19:24
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/login")
    public String login() {
        return "hello world";
    }
}
