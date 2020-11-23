package com.gwy.manager.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;
import com.gwy.manager.entity.Dept;
import com.gwy.manager.entity.Root;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.AdminMapper;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.mapper.RootMapper;
import com.gwy.manager.service.RootService;
import com.gwy.manager.util.MD5Util;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 16:45
 */
@Service
public class RootServiceImpl implements RootService {

    @Autowired
    private RootMapper rootMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public ResultVO login(String account, String password) {
//
//        ResultVO resultVO = new ResultVO();
//
//        Root root = rootMapper.getRoot();
//
//        if (account.equals(root.getAccount())) {
//            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
//        } else if (!root.getPassword().equals(MD5Util.inputToDb(password))) {
//            resultVO.setData(ResponseDataMsg.PasswordIncorrect.getMsg());
//        } else {
//            resultVO.success(ResponseDataMsg.Success.getMsg());
//        }
//
//        return resultVO;
//    }

    @Override
    public ResultVO updatePassword(String password) {

        ResultVO resultVO = new ResultVO();

        int i = rootMapper.updatePassword(passwordEncoder.encode(password));
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }
}
