package com.gwy.manager.aspect;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.util.ResultVOUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Tracy
 * @date 2020/11/21 15:47
 */
@Aspect
@Repository
public class SafetyAspect {

    private Logger logger = LoggerFactory.getLogger(SafetyAspect.class);

    @Pointcut("execution(public com.gwy.manager.dto.ResultVO com.gwy.manager.service.impl.*.*(..))")
    public void pointcut(){}

    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {

        ResultVO resultVO = null;

        Object[] args = pjp.getArgs();

        //参数判空
        for (Object arg : args) {
            if (arg == null) {
                resultVO = ResultVOUtil.error(ResponseDataMsg.BadRequest.getMsg());
                return resultVO;
            }
        }

        try {
            resultVO = (ResultVO) pjp.proceed(args);
        } catch (Throwable throwable) {
            logger.error("Exception Found: {}", throwable.getMessage());
            throwable.printStackTrace();
        }

        return resultVO;
    }
}
