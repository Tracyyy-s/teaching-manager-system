package com.gwy.manager.util;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.enums.ResponseStatus;

/**
 * @author Tracy
 * @date 2020/11/26 0:10
 */
public class ResultVoUtil {

    /**
     * 服务器解析错误请求返回
     * @param object    错误信息
     * @return  结果集
     */
    public static ResultVo error(Object object) {
        ResultVo resultVO = new ResultVo();

        resultVO.setData(object);

        return resultVO;
    }

    /**
     * 服务器处理成功请求
     * @param object    返回结果
     * @return  结果集
     */
    public static ResultVo success(Object object) {
        ResultVo resultVO = new ResultVo();

        resultVO.setResultCode(ResponseStatus.SUCCESS.getCode());
        resultVO.setMessage(ResponseStatus.SUCCESS.getMessage());
        resultVO.success(object);

        return resultVO;
    }
}
