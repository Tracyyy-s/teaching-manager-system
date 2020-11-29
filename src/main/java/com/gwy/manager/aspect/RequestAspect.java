package com.gwy.manager.aspect;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.util.ResultVOUtil;
import com.gwy.manager.util.SysLogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author Tracy
 * @date 2020/11/29 14:41
 */
@Aspect
@Component
public class RequestAspect {

    @Pointcut("execution(public * com.gwy.manager.controller..*(..))")
    public void pointcut(){}

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogUtil logUtil;

    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {

        Object result = null;

        Object[] args = pjp.getArgs();

        try {
            result = pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        ResultVO resultVO;
        if (!(result instanceof String)) {
            resultVO = JSONObject.parseObject(JSONObject.toJSONString(result), ResultVO.class);
        } else {
            resultVO = JSONObject.parseObject((String) result, ResultVO.class);
        }

        //记录日志
        logUtil.recordLog(request, args, resultVO);

        return result;
    }
}
