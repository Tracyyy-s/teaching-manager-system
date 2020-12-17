package com.gwy.manager.handler;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tracy
 * @date 2020/12/15 23:41
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义全局获取异常机制
     * 捕获<p>@Controller</p>异常，返回客户端
     * @return  返回结果
     */
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public ResultVO globalHandler(Exception ex) {
        System.out.println(ex.getMessage());
        return ResultVOUtil.error("Error In Server, Check It");
    }
}
