package com.gwy.manager.util;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseStatus;

/**
 * @author Tracy
 * @date 2020/11/26 0:10
 */
public class ResultVOUtil {

    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();

        resultVO.setData(object);

        return resultVO;
    }

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();

        resultVO.setResultCode(ResponseStatus.SUCCESS.getCode());
        resultVO.setMessage(ResponseStatus.SUCCESS.getMessage());
        resultVO.success(object);

        return resultVO;
    }
}
