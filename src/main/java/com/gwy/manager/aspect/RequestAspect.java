package com.gwy.manager.aspect;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tracy
 * @date 2020/11/29 14:41
 */
@Aspect
@Repository
public class RequestAspect {

    @Pointcut("execution(public * com.gwy.manager.controller..*(..))")
    public void pointcut(){}

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogServiceImpl logService;

    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {

        Object result = null;

        Object[] args = pjp.getArgs();

        //用于记录日志的参数
        Object[] argsForLog = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof MultipartFile) {
                argsForLog[i] = "file";
            } else {
                argsForLog[i] = args[i];
            }
        }

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
        if (resultVO != null) {
            logService.recordLog(request, argsForLog, resultVO);
        }

        return result;
    }
}
