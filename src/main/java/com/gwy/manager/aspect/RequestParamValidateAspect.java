package com.gwy.manager.aspect;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.util.ResultVoUtil;
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
public class RequestParamValidateAspect {

    private Logger logger = LoggerFactory.getLogger(RequestParamValidateAspect.class);

    /**
     * 定义切入点表达式
     */
    @Pointcut("execution(public com.gwy.manager.domain.dto.ResultVo com.gwy.manager.service.impl.*.*(..))")
    public void pointcut(){}

    /**
     * 环绕通知检验参数合法性
     * @param pjp   连接点
     * @return  结果集
     */
    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {

        ResultVo resultVO = null;

        Object[] args = pjp.getArgs();

        //参数判空
        for (Object arg : args) {
            if (arg == null) {
                return ResultVoUtil.error(ResponseDataMsg.BadRequest.getMsg());
            }
        }

        try {
            resultVO = (ResultVo) pjp.proceed(args);
        } catch (Throwable throwable) {
            logger.error("Exception Found: {}", throwable.getMessage());
            throwable.printStackTrace();
        }

        return resultVO;
    }
}
