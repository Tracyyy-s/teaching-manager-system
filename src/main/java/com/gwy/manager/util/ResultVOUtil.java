package com.gwy.manager.util;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseStatus;

/**
 * @author Tracy
 * @date 2020/11/26 0:10
 */
public class ResultVOUtil {

    /**
     * 服务器解析错误请求返回
     * @param object    错误信息
     * @return  结果集
     */
    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();

        resultVO.setData(object);

        return resultVO;
    }

    /**
     * 服务器处理成功请求
     * @param object    返回结果
     * @return  结果集
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();

        resultVO.setResultCode(ResponseStatus.SUCCESS.getCode());
        resultVO.setMessage(ResponseStatus.SUCCESS.getMessage());
        resultVO.success(object);

        return resultVO;
    }
}
