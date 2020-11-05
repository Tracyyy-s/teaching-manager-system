package com.gwy.manager.controller;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.service.impl.TermServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tracy
 * @date 2020/11/5 13:57
 */
@RestController
public class BaseController {

    @Autowired
    private TermServiceImpl termService;

    /**
     * 获得所有的学期信息
     * @return 返回结果
     */
    @PostMapping("/getTerms")
    public ResultVO getTerms() {
        return termService.getTerms();
    }
}
