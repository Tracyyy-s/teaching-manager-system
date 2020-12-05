package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.SysLog;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tracy
 * @date 2020/12/5 13:06
 */
public interface SysLogService {

    /**
     * 使用日志工具记录日志
     * @param request   请求
     * @param args  参数
     * @param resultVO  请求返回结果
     */
    void recordLog(HttpServletRequest request, Object[] args, ResultVO resultVO);

    /**
     * 添加日志
     * @param sysLog    预添加的日志
     */
    void insertLog(SysLog sysLog);

    /**
     * 获得每类日志的数量
     * @return  结果集
     */
    ResultVO getLogTypeAndCount();

    /**
     * 通过日志类型获得日志
     * @param type  关键字
     * @param pageNum   页码
     * @param pageSize  页大小
     * @return  结果集
     */
    ResultVO getLogInfoByType(String type, int pageNum, int pageSize);

    /**
     * 分页获得日志
     * @param pageNum   页码
     * @param pageSize  每页个数
     * @return  结果集
     */
    ResultVO getLogs(int pageNum, int pageSize);
}
